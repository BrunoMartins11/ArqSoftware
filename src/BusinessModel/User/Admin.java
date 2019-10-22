package BusinessModel.User;

import BusinessModel.Assets.Asset;
import BusinessModel.Report.Bug;

import java.util.List;

public class Admin extends User {

	private String sudopass;

	public Admin(String username, String email, String password) {
		super(username, email, password);
	}

	public boolean loginAdmin(String sudoPass){
		throw new UnsupportedOperationException();
	}

	public List<Asset> checkMarket(){
		throw new UnsupportedOperationException();
	}

	public List<Bug> getBugsList(){
		throw new UnsupportedOperationException();
	}
}