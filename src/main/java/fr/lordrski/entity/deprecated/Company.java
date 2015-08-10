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
package fr.lordrski.entity.deprecated;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import fr.lordrski.dao.impl.CompanyDaoImpl;

/**
 * Entité représentant une compagnie.
 */
@Deprecated
@DatabaseTable(tableName = "companies", daoClass = CompanyDaoImpl.class)
public class Company {
	
	@DatabaseField(id = true, columnName = "company_id")
	private String companyId;
	@DatabaseField(columnName = "company_label")
	private String companyLabel;
	@DatabaseField(columnName = "address")
	private String address;
	@DatabaseField(columnName = "additionnal_address")
	private String additionnalAddress;
	@DatabaseField(columnName = "zip_code")
	private String zipCode;
	
	public Company() {}
	
	public Company(String companyId, String companyLabel, String address, String additionnalAddress, String zipCode) {
		this.companyId = companyId;
		this.companyLabel = companyLabel;
		this.address = address;
		this.additionnalAddress = additionnalAddress;
		this.zipCode = zipCode;
	}
	
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyLabel
	 */
	public String getCompanyLabel() {
		return companyLabel;
	}

	/**
	 * @param companyLabel the companyLabel to set
	 */
	public void setCompanyLabel(String companyLabel) {
		this.companyLabel = companyLabel;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the additionnalAddress
	 */
	public String getAdditionnalAddress() {
		return additionnalAddress;
	}

	/**
	 * @param additionnalAddress the additionnalAddress to set
	 */
	public void setAdditionnalAddress(String additionnalAddress) {
		this.additionnalAddress = additionnalAddress;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Company) && companyId.equals(((Company) o).companyId);
	}
	
	@Override
	public int hashCode() {
		return companyId == null ? 0 : companyId.hashCode();
	}
	
	@Override
	public String toString() {
		return "Company[companyId=" + companyId+ ", companyLabel=" + companyLabel
						+ ", address=" + address + ", additionnalAddress=" + additionnalAddress + ", zipCode=" + zipCode + "]";
	}
	
}
