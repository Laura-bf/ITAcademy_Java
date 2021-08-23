package N2;

import java.util.Date;

public class License {
	
	protected String licenseId;
	protected int licenseType;
	protected String fullName;
	protected Date expiryDate;
	
	public License() {};
	public License(String licenseId, int licenseType, Date expiryDate) {
		if(checkLicenseId(licenseId)) {
			this.licenseId = licenseId;
		}
		this.licenseType = licenseType;
		this.expiryDate = expiryDate;
	}
	/**
	 * @param licenseId
	 * @param licenseType
	 * @param fullName
	 * @param expiryDate
	 */
	public License(String licenseId, int licenseType, String fullName, Date expiryDate) {
		if(checkLicenseId(licenseId)) {
			this.licenseId = licenseId;
		}
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
	public License(String licenseId, int licenseType, String name, String surname, Date expiryDate) {
		if(checkLicenseId(licenseId)) {
			this.licenseId = licenseId;
		}
		this.licenseType = licenseType;
		this.fullName = name.concat(" "+surname);
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
	public int getLicenseType() {
		return licenseType;
	}
	/**
	 * @param licenseType the licenseType to set
	 */
	public void setLicenseType(int licenseType) {
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
	/**Método para comprobar que el carnet tiene un formato válido
	 * @param licenseId
	 * @return
	 */
	public static boolean checkLicenseId(String licenseId) {
		boolean validLicenseId = false;
		if(licenseId.length()==9) {
			validLicenseId = true;
		} else {
			System.err.println("Formato incorrecto. Debe tener 8 números y 1 letra final.");
		}
		if(validLicenseId) {
			if(Character.isDigit(licenseId.charAt(8))) {
				validLicenseId = false;
				System.err.println("Formato incorrecto. Debe tener 8 números y 1 letra final.");
			} else {
				for(int i = 0; i<8; i++) {
					if(!Character.isDigit(licenseId.charAt(i))) {
						validLicenseId = false;
						System.err.println("Formato incorrecto. Debe tener 8 números y 1 letra final.");
					}
				}
			}	
		}
		return validLicenseId;
	}

	@Override
	public String toString() {
		return "License: LicenseId: " + licenseId + ", Type: " + licenseType + ", fullName: " + fullName
				+ ", expiryDate: " + expiryDate;
	}

}
