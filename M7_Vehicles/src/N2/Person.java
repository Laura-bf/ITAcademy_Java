package N2;

import java.util.Date;

public abstract class Person implements DrivingAble {
	
	protected String name;
	protected String surname;
	protected Date birthDate;
	
	public Person() {
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public License createLicense(String licenseId, int licenseType, Date expiryDate) {
		License license = new License(licenseId, licenseType, expiryDate);
		license.setFullName(this.name.concat(" " + this.surname));
		return license;
	}
	
	@Override
	public String toString() {
		return "Person\nName: " + name + ", Surname: " + surname + ", BirthDate: " + birthDate;
	}
}
