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

		bw = new BackgroundWorker(assets);
		Thread t = new Thread(bw);
		t.start();
	}

	// GETS USERS
	public Map<Integer, User> getUsers() {
		return this.users;
	}

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
		if(u instanceof Investor)
		{
			Investor investor = (Investor) u;
			ret = investor.allInvested();
		}
		return ret;
	}

	// GETS ASSETS
	public Map<Integer, Asset> getAssets() {
		return  this.assets;
	}

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

	// GETS CFD'S
	public Map<Integer, CFD> getCfds() {
		return  this.cfds;
	}

	// GETS BUG'S
	public List<Bug> getBugs() {
		return this.bugs;
	}

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

	public int getUserID(String email)
	{
		//TODO - CHECK FUNCTION
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

	// positionType: 1 - Compra; 2 - Venda
	public void createCFD(int userID, int positionType, int assetID, double numberOfAssets, double tp, double sl)
	{
		// TODO - CHECK FUNCTION
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
			pos = Position.LONG;
		}
		else
		{
			pos = Position.SHORT;
		}
		return pos;
	}

	public void openCFD(CFD cfd, int portfolioId){
		cfds.put(cfd.getId(), cfd);
		(new CFDdao()).saveToPortfolio(cfd, portfolioId);
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

	public void closePosition(int userID, int cfdToClose)
	{ //TODO CHECK
		Investor inv = (Investor) users.get(userID);
		CFD c = cfds.get(cfdToClose);
		closeCFD(c);
	}

	public void closeCFD(CFD cfd){
		//TODO Calcular o que ganhou/perdeu
		(new CFDdao()).delete(cfd);
		cfds.remove(cfd.getId());
	}

	public List<Asset> getInvestorWatchList(int id){
		Investor investor = (Investor) users.get(id);
		List<Asset> watchList = new ArrayList<>();
		for (Integer i: investor.getPortfolio().getWatchList()) {
			watchList.add(assets.get(i));
		}
		return watchList;
	}

	public void reportBug(int idUser, String text){
		Bug b;
		bugs.add(b = (new Bug(bugs.size()+1, text, LocalDateTime.now(), idUser)));
		(new BugDAO()).save(b);
	}

	public boolean checkUserCredit(int userID, int assetID, double numberOfAssets)
	{ // TODO CHECK
		boolean ret = false;

		double credit = 0;
		double finalVal = 0;
		double buyValue = assets.get(assetID).getValue() * numberOfAssets;
		User u = this.users.get(userID);

		if(u instanceof Investor)
		{
			Investor i = (Investor) u;
			credit = i.getCredit();
			if(credit >= buyValue)
			{
				ret = true;
				i = (Investor) users.get(userID);
				i.takeCredit(buyValue);
			}
		}

		return ret;
	}

	public List<CFD> getPortfolio(int userID)
	{ // TODO CHECK
		User u = users.get(userID);
		List<CFD> cfdList = new ArrayList<>();
		if( u instanceof Investor)
		{
			Investor investor = (Investor) u;
			List<Integer> cfdIdsList = investor.getPortfolio().getCFDs();
			for(int i : cfdIdsList)
			{
				cfdList.add(cfds.get(i));
			}
		}
		return cfdList;
	}

	public void removeItemFromWatchList(int userID, int assetId)
	{ // TODO CHECK
		Investor inv = (Investor) users.get(userID);
		inv.removeWatchList(assetId);
	}

	public void addItemToWatchList(int userID, int assetID){
		Investor inv = (Investor) users.get(userID);
		inv.addToWatchList(assetID);
	}
}