/**
 * This file is part of webapp-skeleton.
 *
 * webapp-skeleton is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * webapp-skeleton is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with webapp-skeleton.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.lordrski.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User Entity
 */
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
	public boolean equals(Object o) {
		return (o instanceof User) && login.equals(((User)o).login);
	}
	
	@Override
	public int hashCode() {
		return login == null ? 0 : login.hashCode();
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", birthday=" + birthday + ", email=" + email + "]";
	}
}
	
