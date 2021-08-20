package N1;

import javax.swing.JOptionPane;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) {
		this.brand = brand;
		if(diameter>0.4 || diameter<4){
			this.diameter = diameter;
		} 
	}
	
	
	@Override
	public String toString() {
		return "Wheel: brand=" + brand + ", diameter=" + diameter;
	}
	
}
