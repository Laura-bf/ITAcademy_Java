package N2;

import java.util.Date;

public interface DrivingAble {
	public License createLicense(String licenseId, int licenseType, Date expiryDate);
}