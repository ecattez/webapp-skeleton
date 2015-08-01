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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.lordrski.dao.impl.UserDaoImpl;

/**
 * Entité représentant un utilisateur d'une compagnie.
 */
@XmlRootElement
@DatabaseTable(tableName = "users", daoClass = UserDaoImpl.class)
public class User {
	
	@DatabaseField(columnName = "company_id", uniqueCombo = true)
	private String company;
	@DatabaseField(columnName = "group_id", uniqueCombo = true)
	private String group;
	@DatabaseField(columnName = "login", id = true, uniqueCombo = true)
	private String login;
	@DatabaseField(columnName = "password")
	private String password;
	@DatabaseField(columnName = "firstname")
	private String firstName;
	@DatabaseField(columnName = "lastname")
	private String lastName;
	@DatabaseField(columnName = "phonenumber")
	private String phoneNumber;
	@DatabaseField(columnName = "email")
	private String email;
	
	public User() {}
	
	public User(String company, String group, String login, String password, String firstName, String lastName, String phoneNumber, String email) {
		this.company = company;
		this.group = group;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param group the group to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * Saisi le mot de passe du User
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
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Saisi le prénom du User
	 * 
	 * @param firstName le prénom du User
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Retourne le nom de famille du User
	 * 
	 * @return le nom de famille du User
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Saisi le nom de famille du User
	 * 
	 * @param lastName le nom de famille du User
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retourne le numéro de téléphone du User
	 * 
	 * @return le numéro de téléphone du User
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Saisi le numéro de téléphone du User
	 * 
	 * @param phoneNumber le numéro de téléphone du User
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * Saisi l'adresse email du User
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
		return "User [company=" + company + ", group=" + group + ", login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthday=" + phoneNumber + ", email=" + email + "]";
	}
}
	
