package N3;

import java.util.Date;

public class Driver extends Person {
	
	protected License license = new License();
	
	public Driver(String name, String surname, Date birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.license.setFullName(name.concat(" "+surname));
	}
	
	public void setLicense(License license) {
		this.license = license;
	}
	
	public int getLicenseType() {
		return license.getLicenseType();
	}

	@Override
	public String toString() {
		return "Name:" + name + ", Surname: " 
				+ surname + ", BirthDate: " + birthDate +"\n"+ license.toString();
	}
}
