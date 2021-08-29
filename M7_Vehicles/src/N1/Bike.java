package N1;

public class Bike extends Vehicle {

	public Bike(String plate, String brand, String color) {
		super(plate, brand, color);
	}

	public void addWheels(Wheel wheel) {
		this.wheels.add(wheel);
	}
	
	@Override
	public String toString() {
		return "Bike:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels;
	}
}
