package BusinessModel;

import BusinessModel.Report.Bug;
import BusinessModel.User.*;
import BusinessModel.Assets.*;
import BusinessModel.Trading.*;
import Data.UserDAO;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class ESSTrading {

	private Map<String, User> users;
	private Map<String, Asset> assets;
	private Map<Integer, CFD> cfds;
	private List<Bug> bugs;

	public Map<String, User> getUsers() {
		throw new UnsupportedOperationException();
	}

	public Map<String, Asset> getAssets() {
		throw new UnsupportedOperationException();	}

	public Map<Integer, CFD> getCfds() {
		throw new UnsupportedOperationException();	}

	public List<Bug> getBugs() {
		throw new UnsupportedOperationException();	}

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		User u = userDAO.get(0);
		System.out.println(u.getEmail());
	}

}