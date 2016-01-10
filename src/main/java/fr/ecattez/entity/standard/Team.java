package fr.ecattez.entity.standard;

import com.j256.ormlite.field.DatabaseField;

public class Team {
	
	@DatabaseField(columnName = "tno", id = true)
	private int id;
	@DatabaseField(columnName = "ono", foreign = true)
	private int organisationId;
	@DatabaseField(columnName = "team_name")
	private String teamName;
	
	public Team() {}

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
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
