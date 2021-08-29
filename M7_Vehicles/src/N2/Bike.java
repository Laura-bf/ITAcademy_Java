package N2;

import java.util.List;

public class Bike extends Vehicle {
	
	private final static int LICENSE = 1;
	
	public Bike() {
	}

	public static int getLicense() {
		return LICENSE;
	}
	
	public void addTwoWheels(List<Wheel> wheels) {
		this.wheels.add(wheels.get(0));
		this.wheels.add(wheels.get(1));
	}

	public void addWheels(Wheel frontWheel, Wheel backWheel) {
		this.wheels.add(frontWheel);
		this.wheels.add(backWheel);
	}
	
	@Override
	public String toString() {
		return "Bike:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels + "\nOwner:\n"+ owner + "\nDriver:\n" + driver;
	}
}