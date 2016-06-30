package racom.be.model;

import org.json.JSONObject;

import com.stormpath.sdk.account.Account;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = -8292094708632827965L;

	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;

	public User() {

	}

	public User(Account account) {
		this.userName = account.getUsername();
		this.firstName = account.getGivenName();
		this.lastName = account.getSurname();
		this.email = account.getEmail();
		this.password = "";

	}

	public User(JSONObject asJson) {
		this.userName = asJson.getString("userName");
		this.firstName = asJson.getString("firstName");
		this.lastName = asJson.getString("lastName");
		this.email = asJson.getString("email");
		this.password = asJson.getString("password");
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}