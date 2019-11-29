package BusinessModel;

import BusinessModel.Report.Bug;
import BusinessModel.User.*;
import BusinessModel.Assets.*;
import BusinessModel.Trading.*;

import Data.AssetDAO;
import Data.BugDAO;
import Data.CFDdao;
import Data.UserDAO;
import Services.BackgroundWorker;

import java.time.LocalDateTime;
import java.util.*;

public class ESSTrading {

	private Map<Integer, User> users;
	private Map<Integer, Asset> assets;
	private Map<Integer, CFD> cfds;
	private List<Bug> bugs;
	private BackgroundWorker bw;

	public ESSTrading(){

		users = new HashMap<>();
		UserDAO userDAO = new UserDAO();
		for (User user: userDAO.getAll()) {
			users.put(user.getId(), user);
		}

		assets = new HashMap<>();
		AssetDAO assetDAO = new AssetDAO();
		for(Asset asset: assetDAO.getAll()){
			assets.put(asset.getId(), asset);
		}

		cfds = new HashMap<>();
		CFDdao cfddao = new CFDdao();
		for (CFD cfd: cfddao.getAll()){
			cfds.put(cfd.getId(), cfd);
		}

		bugs = (new BugDAO()).getAll();

		bw = new BackgroundWorker();
		for (Asset a : this.assets.values())
		{
			bw.addObserver(a);
		}
		Thread t = new Thread(bw);
		t.start();
	}

	// GETS
	public Map<Integer, User> getUsers() {
		return this.users;
	}
	public Map<Integer, CFD> getCfds() {
		return  this.cfds;
	}
	public List<Bug> getBugs() {
		return this.bugs;
	}
	public Map<Integer, Asset> getAssets() {
		return  this.assets;
	}

    public BackgroundWorker getBw() {
        return bw;
    }

    public int getUserID(String email)
	{
		int id = -1;
		for(User u : users.values())
		{
			if(u.getEmail().equals(email))
			{
				id = u.getId();
			}
		}
		return id;
	}

	public List<CFD> getPortfolio(int userID)
	{
		User u = users.get(userID);
		List<CFD> cfdList = new ArrayList<>();
		if( u instanceof Investor)
		{
			Investor investor = (Investor) u;
			List<Integer> cfdIdsList = investor.getPortfolio().getCFDs();
			for(int i : cfdIdsList)
			{
				if(cfds.get(i) != null)
				{
					cfdList.add(cfds.get(i)); //TODO VALIDATION added
				}
			}
		}
		return cfdList;
	}

	// GET ASSETS
	public Map<Integer,Asset> getAssetsByType(String type)
	{
		Map<Integer,Asset> ret = new HashMap<>();

		for(Asset a : assets.values())
		{
			if(a.getType().equals(AssetType.valueOf(type)))
			{
				ret.put(a.getId(),a);
			}
		}

		return ret;
	}

	// USER CREDIT
	public double getUserCredit(int userID)
	{
		User u = users.get(userID);
		double ret = 0;
		if(u instanceof Investor)
		{
			Investor investor = (Investor) u;
			ret = investor.getCredit();
		}
		return ret;
	}

	public double getTotalInvestedByUser(int userID)
	{
		User u = users.get(userID);
		double ret = 0;
		List<Integer> ids;
		if(u instanceof Investor)
		{
			Investor investor = (Investor) u;
			ids = investor.getPortfolio().getCFDs();
			if(ids.size() > 0)
			{
				for(Integer i : ids)
				{
					CFD c = this.cfds.get(i);
					if(c != null)
					{
						ret += (c.getPriceAcquisition()*c.getQuantity());
					}
				}
			}
		}
		return ret;
	}

	public void insertCredit(int userID, Double value){
		User u = users.get(userID);
		if(u instanceof Investor)
			((Investor) u).insertCredit(value);
	}

	public void takeCredit(int userID, Double value){
		User u = users.get(userID);
		if(u instanceof Investor)
			((Investor) u).takeCredit(value);
	}

	public boolean checkUserCredit(int userID, int assetID, double numberOfAssets, int positionType)
	{
		boolean ret = false;

		double credit = 0;
		double buyValue = assets.get(assetID).getValue() * numberOfAssets;
		User u = this.users.get(userID);

		if(u instanceof Investor)
		{
			Investor i = (Investor) u;
			credit = i.getCredit();
			if(credit >= buyValue)
			{
				if(positionType == 1)
				{
					ret = true;
					i = (Investor) users.get(userID);
					i.takeCredit(buyValue);
				}
				else
				{
					ret = checkSellPosition(userID,assetID);
				}
			}
		}

		return ret;
	}

	// LOGIN REGISTRATION STUFF
	public User loginUser(String email, String password){
	    User user = null;
		for (User u: users.values()) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password))
				user = u;
		}
		return user;
	}

	public boolean saveNewUser(String email, String password){
		double credit = 0;
		Portfolio p = new Portfolio();
		User u = new Investor(users.size()+1, email, email, password, p, credit);
		users.put(users.size()+1, u);
		UserDAO dao = new UserDAO();
		dao.save(u);
		return true;
	}

	// CFD
	public void createCFD(int userID, int positionType, int assetID, double numberOfAssets, double tp, double sl)
	{
		int id = cfds.size() + 1;
		Position pos = getPositionType(positionType);
		double assetPrice = assets.get(assetID).getValue();

		CFD cfd = new CFD(id,tp,sl,assetPrice,numberOfAssets,LocalDateTime.now(),pos,assetID);
		int portfolioID = ((Investor) users.get(userID)).getPortfolioId();
		((Investor) users.get(userID)).getPortfolio().addCFD(id);
		openCFD(cfd, portfolioID);
	}

	private Position getPositionType(int input)
	{
		Position pos;
		if(input == 1)
		{
			pos = Position.LONG; // COMPRA
		}
		else
		{
			pos = Position.SHORT; // VENDA
		}
		return pos;
	}

	public void openCFD(CFD cfd, int portfolioId){
		cfds.put(cfd.getId(), cfd);
		(new CFDdao()).saveToPortfolio(cfd, portfolioId);
	}

	// CLOSE POSITION //
	public boolean checkSellPosition(int userID, int assetID)
	{
		boolean ret = false;
		Investor inv = (Investor) users.get(userID);

		if(assets.get(assetID).getValue() <= inv.getCredit())
		{
			ret = true;
		}

		return ret;
	}

	public void closePosition(int userID, int cfdToClose)
	{
		CFD c = cfds.get(cfdToClose);
		double credit = 0;
		double aquisitionPrice = c.getPriceAcquisition()*c.getQuantity();

		if(c.getPosition().equals(Position.LONG))
		{
			credit = aquisitionPrice - assets.get(cfdToClose).getValue();
			insertCredit(userID,credit);
		}
		else
		{
			credit = assets.get(cfdToClose).getValue() - aquisitionPrice;
			insertCredit(userID,credit);
		}
		closeCFD(c);
	}

	public void closeCFD(CFD cfd){
		(new CFDdao()).delete(cfd);
		cfds.remove(cfd.getId());
	}

	// WATCHLIST //
	public List<Asset> getInvestorWatchList(int id){
		Investor investor = (Investor) users.get(id);
		List<Asset> watchList = new ArrayList<>();
		for (Integer i: investor.getPortfolio().getWatchList()) {
			if(assets.get(i) != null)
			{
				watchList.add(assets.get(i)); //TODO ADDED VALIDATION
			}
		}
		return watchList;
	}

	public void removeItemFromWatchList(int userID, int assetId)
	{
		Investor inv = (Investor) users.get(userID);
		inv.removeWatchList(assetId);
	}

	public void addItemToWatchList(int userID, int assetID){
		Investor inv = (Investor) users.get(userID);
		inv.addToWatchList(assetID);
	}

	// BUG REPORT
	public void reportBug(int idUser, String text){
		Bug b;
		bugs.add(b = (new Bug(bugs.size()+1, text, LocalDateTime.now(), idUser)));
		(new BugDAO()).save(b);
	}

	// THREAD MANAGE //
	public void stopThread()
	{
		bw.stop();
	}
}