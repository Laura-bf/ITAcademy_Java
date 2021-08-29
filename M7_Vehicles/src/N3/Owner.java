package N3;

import java.util.Date;

public class Owner extends Person {
	
	protected License license = new License();
	protected boolean hasInsurance;
	protected boolean hasGarage;
	
	public Owner(String name, String surname, Date birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}
	
	public void setLicense(License license) {
		this.license = license;
	}
	

	public int getLicenseType() {
		return license.getLicenseType();
	}

	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Surname: " + surname 
					+ ", BirthDate: " + birthDate+ ", Insurance?: " + hasInsurance + ", Garage?: " + hasGarage + "\n" + license.toString();
	}
}

