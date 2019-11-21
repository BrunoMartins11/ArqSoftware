package BusinessModel;

import BusinessModel.Report.Bug;
import BusinessModel.User.*;
import BusinessModel.Assets.*;
import BusinessModel.Trading.*;

import Data.AssetDAO;
import Data.BugDAO;
import Data.CFDdao;
import Data.UserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ESSTrading {

	private Map<Integer, User> users;
	private Map<Integer, Asset> assets;
	private Map<Integer, CFD> cfds;
	private List<Bug> bugs;

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
	}

	// GETS USERS
	public Map<Integer, User> getUsers() {
		return this.users;
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

	public boolean loginUser(String email, String password){
		for (User u: users.values()) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password))
				return true;
		}
		return  false;
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
}