package N1;

import java.util.ArrayList;

public abstract class Vehicle {

	protected String plate;
	protected String brand;
	protected String color;
	protected ArrayList<Wheel> wheels = new ArrayList<Wheel>();

	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
}
