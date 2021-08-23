package N2;

import java.util.Date;

public class Owner extends Person {
	/**
	 * licencia de conducir
	 */
	protected License license = new License();
	/**
	 * si tiene o no tiene seguro
	 */
	protected boolean hasInsurance;
	/**
	 * si tiene o no tiene garaje propio
	 */
	protected boolean hasGarage;
	
	/**
	 * Constructor por defecto
	 */
	public Owner() {
	}
	/**
	 * Constructor con 3 parámetros 
	 * @param name
	 * @param surname
	 * @param birthDate
	 */
	public Owner(String name, String surname, Date birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}
	/**
	 * Constructor con 4 parámetros 
	 * @param name
	 * @param surname
	 * @param birthDate
	 * @param license
	 */
	
	
	public Owner(String name, String surname, Date birthDate, String licenseId) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.license.setLicenseId(licenseId);
		this.license.setFullName(name.concat(surname));
	}
	/**
	 * @param name
	 * @param surname
	 * @param birthDate
	 * @param license
	 */
	public Owner(String name, String surname, Date birthDate, License license) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.license = license;
	}
	/**
	 * Constructor con todos los parámetros (6)
	 * @param name
	 * @param surname
	 * @param birthDate
	 * @param license
	 * @param hasInsurance
	 * @param hasGarage
	 */
	public Owner(String name, String surname, Date birthDate, String licenseId, boolean hasInsurance, boolean hasGarage) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.license.setLicenseId(licenseId);
		this.license.setFullName(name.concat(surname));
		this.hasInsurance = hasInsurance;
		this.hasGarage = hasGarage;
	}

	
	/**
	 * @return the license
	 */
	public License getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(License license) {
		this.license = license;
	}
	/**
	 * @return
	 * @see N2.License#getLicenseId()
	 */
	public String getLicenseId() {
		return license.getLicenseId();
	}
	/**
	 * @param licenseId
	 * @see N2.License#setLicenseId(java.lang.String)
	 */
	public void setLicenseId(String licenseId) {
		license.setLicenseId(licenseId);
	}
	/**
	 * @return
	 * @see N2.License#getLicenseType()
	 */
	public int getLicenseType() {
		return license.getLicenseType();
	}
	/**
	 * @param licenseType
	 * @see N2.License#setLicenseType(int)
	 */
	public void setLicenseType(int licenseType) {
		license.setLicenseType(licenseType);
	}
	/**
	 * @return
	 * @see N2.License#getFullName()
	 */
	public String getFullName() {
		return license.getFullName();
	}
	/**
	 * @param fullName
	 * @see N2.License#setFullName(java.lang.String)
	 */
	public void setFullName(String fullName) {
		license.setFullName(fullName);
	}
	public void setFullName(String name, String surname) {
		license.setFullName(name.concat(surname));
	}
	/**
	 * @return
	 * @see N2.License#getExpiryDate()
	 */
	public Date getExpiryDate() {
		return license.getExpiryDate();
	}
	/**
	 * @param expiryDate
	 * @see N2.License#setExpiryDate(java.util.Date)
	 */
	public void setExpiryDate(Date expiryDate) {
		license.setExpiryDate(expiryDate);
	}
	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return license.equals(obj);
	}
		
	/**
	 * @return the hasInsurance
	 */
	public boolean isHasInsurance() {
		return hasInsurance;
	}
	/**
	 * @param hasInsurance the hasInsurance to set
	 */
	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	/**
	 * @return the hasGarage
	 */
	public boolean isHasGarage() {
		return hasGarage;
	}
	/**
	 * @param hasGarage the hasGarage to set
	 */
	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Surname: " + surname 
					+ ", BirthDate: " + birthDate+ ", Insurance?: " + hasInsurance + ", Garage?: " + hasGarage + "\n" + license.toString();
	}

}
