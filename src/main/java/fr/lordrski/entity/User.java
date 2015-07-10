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

	/**
	 * Retourne le pseudo du User
	 * 
	 * @return le pseudo du User
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Saisi le pseudo du User
	 * 
	 * @param login le pseudo du User
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Retourne le mot de passe du User
	 * 
	 * @return le mot de passe du User
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Saisie le mot de passe du User
	 * 
	 * @param password le mot de passe du User
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retourne le prénom du User
	 * 
	 * @return le prénom du User
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Saisie le prénom du User
	 * 
	 * @param firstname le prénom du User
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Retourne le nom de famille du User
	 * 
	 * @return le nom de famille du User
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * Saisie le nom de famille du User
	 * 
	 * @param lastname le nom de famille du User
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Retourne la date de naissance du User
	 * 
	 * @return la date de naissance du User
	 */
	public String getBirthday() {
		return this.birthday;
	}

	/**
	 * Saisie la date de naissance du User
	 * 
	 * @param birthday la date de naissance du User
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Retourne l'adresse email du User
	 * 
	 * @return l'adresse email du User
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Saisie l'adresse email du User
	 * 
	 * @param email l'adresse email du User
	 */
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
	
