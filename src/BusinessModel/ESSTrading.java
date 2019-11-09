package BusinessModel;

import BusinessModel.Report.Bug;
import BusinessModel.User.*;
import BusinessModel.Assets.*;
import BusinessModel.Trading.*;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class ESSTrading {

	private Map<String, User> users;
	private Map<String, Asset> assets;
	private Map<Integer, CFD> cfds;
	private List<Bug> bugs;

	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost:3306/ESS_Trading";
	private static final String user = "root";
	private static final String password = "Mysql123.";

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;

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
		String query = "show tables;";

		try {
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);

			System.out.println(rs.first());

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			//close connection ,stmt and resultset here
			try { con.close(); } catch(SQLException se) { /*can't do anything */ }
			try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		}
	}

}