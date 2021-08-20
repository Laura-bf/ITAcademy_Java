package N1;

import java.util.List;

import javax.swing.JOptionPane;

public class Car extends Vehicle {

	public Car(String plate, String brand, String color) {
		super(plate, brand, color);
	}

	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels){
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}

	public void addTwoWheels(List<Wheel> wheels){
		if (wheels.size() != 2)
			JOptionPane.showMessageDialog(null, "Máximo: 2 ruedas por eje", "ERROR",0);

		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if (!(rightWheel.toString()).equals(leftWheel.toString()))
			JOptionPane.showMessageDialog(null, "Las ruedas de un mismo eje, delantero o trasero, deben ser iguales", "ERROR", 0);

		this.wheels.add(leftWheel);
		this.wheels.add(rightWheel);
	}

	@Override
	public String toString() {
		return "Car:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels;
	}


		

}
