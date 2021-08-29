package N2;

import java.util.List;

public class Car extends Vehicle {
	
	private final static int LICENSE = 2;
	
	public Car() {
	}
	
	public static int getLicense() {
		return LICENSE;
	}
	
	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels){
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}
	
	public void addTwoWheels(List<Wheel> wheels){
		if (wheels.size() != 2)
			System.err.println("Máximo: 2 ruedas por eje");
			
		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if(rightWheel.equals(leftWheel)) {
			this.wheels.add(leftWheel);
			this.wheels.add(rightWheel);
		} else {
			this.wheels.clear();
			System.err.println("RUEDAS NO REGISTRADAS - Las ruedas de un mismo eje, delantero o trasero, deben ser iguales");
		}
	}

	@Override
	public String toString() {
		return "Car:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels + "\nOwner:\n"+ owner + "\nDriver:\n" + driver;
	}


		

}
