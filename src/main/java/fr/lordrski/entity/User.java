package fr.lordrski.entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String birthday;
	private String email;
	
	public User() {}
	
	public User(String login, String password, String firstname, String lastname, String birthday, String email) {
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

//	@JsonIgnore
//	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o){
		return (o instanceof User) && login.equals(((User)o).login);
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", birthday=" + birthday + ", email=" + email + "]";
	}
}
	
