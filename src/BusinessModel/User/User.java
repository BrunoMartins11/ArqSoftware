package BusinessModel.User;

public abstract class User {

	private int id;
	private String username;
	private String email;
	private String password;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public boolean login(String username, String password){
		throw new UnsupportedOperationException();
	}
}