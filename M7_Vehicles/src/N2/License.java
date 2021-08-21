package N2;

import java.util.Date;

public class License {
	
	protected String licenseId;
	protected char licenseType;
	protected String fullName;
	protected Date expiryDate;
	
	public License(String licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @param licenseId
	 * @param licenseType
	 * @param fullName
	 * @param expiryDate
	 */
	public License(String licenseId, char licenseType, String fullName, Date expiryDate) {
		this.licenseId = licenseId;
		this.licenseType = licenseType;
		this.fullName = fullName;
		this.expiryDate = expiryDate;
	}
	/**
	 * Constructor con fullname a partir de nombre y apellido
	 * @param licenseId
	 * @param licenseType
	 * @param name
	 * @param surname
	 * @param expiryDate
	 */
	public License(String licenseId, char licenseType, String name, String surname, Date expiryDate) {
		this.licenseId = licenseId;
		this.licenseType = licenseType;
		this.fullName = name.concat(surname);
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the licenseId
	 */
	public String getLicenseId() {
		return licenseId;
	}
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @return the licenseType
	 */
	public char getLicenseType() {
		return licenseType;
	}
	/**
	 * @param licenseType the licenseType to set
	 */
	public void setLicenseType(char licenseType) {
		this.licenseType = licenseType;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
