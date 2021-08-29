package N2;

public class Wheel {
	
	private String brand;
	private double diameter;
	
	public Wheel() {	
	}
	
	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double getDiameter() {
		return diameter;
	}
	
	public void setDiameter(double diameter) {
		if (checkDiameter(diameter)) {
			this.diameter = diameter;
		} else {
			System.err.println("ERROR - Diámetro permitido entre 0.4 y 4");
		}
	}
	
	public boolean checkDiameter(double diameter) {
		if (diameter > 0.4 && diameter < 4) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Wheel: brand=" + brand + ", diameter=" + diameter;
	}
	
}
