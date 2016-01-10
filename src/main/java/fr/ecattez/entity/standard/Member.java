package fr.ecattez.entity.standard;

import com.j256.ormlite.field.DatabaseField;

public class Member {
	
	@DatabaseField(columnName = "mno", id = true)
	private int id;
	@DatabaseField(columnName = "ono", foreign = true)
	private int organisationId;
	@DatabaseField(columnName = "first_name")
	private String firstName;
	@DatabaseField(columnName = "last_name")
	private String lastName;
	@DatabaseField(columnName = "email")
	private String email;
	@DatabaseField(columnName = "phone")
	private String phone;
	
	public Member() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the organisationId
	 */
	public int getOrganisationId() {
		return organisationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
