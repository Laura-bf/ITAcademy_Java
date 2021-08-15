package N1;

import java.util.List;

import exceptions.WrongWheelsException;

public class Car extends Vehicle {

	public Car(String plate, String brand, String color) {
		super(plate, brand, color);
	}

	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels) throws Exception {
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}

	public void addTwoWheels(List<Wheel> wheels) throws WrongWheelsException {
		if (wheels.size() != 2)
			throw new WrongWheelsException("Máximo: 2 ruedas por eje");

		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if (!(rightWheel.toString()).equals(leftWheel.toString()))
			throw new WrongWheelsException("Las ruedas de un mismo eje, delantero o trasero, deben ser iguales");

		this.wheels.add(leftWheel);
		this.wheels.add(rightWheel);
	}

	@Override
	public String toString() {
		return "Car:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels;
	}


		

}