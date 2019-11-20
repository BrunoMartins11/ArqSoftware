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

	public Map<Integer, User> getUsers() {
		return this.users;
	}

	public Map<Integer, Asset> getAssets() {
		return  this.assets;
	}

	public Map<Integer, CFD> getCfds() {
		return  this.cfds;
	}

	public List<Bug> getBugs() {
		return this.bugs;
	}

}