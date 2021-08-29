package N3;

import java.util.Date;

public class License {
	
	protected String licenseId;
	protected int licenseType;
	protected String fullName;
	protected Date expiryDate;
	
	public License() {}
	
	public License(String licenseId, int licenseType, Date expiryDate) {
		if(checkLicenseId(licenseId)) {
			this.licenseId = licenseId;
		}
		this.licenseType = licenseType;
		this.expiryDate = expiryDate;
	}

	public int getLicenseType() {
		return licenseType;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
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
