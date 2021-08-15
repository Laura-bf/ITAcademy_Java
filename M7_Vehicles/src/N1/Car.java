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
			throw new WrongWheelsException("M�ximo: 2 ruedas por eje");

		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);


		/*equals() cuando compara objetos verifica que tengan la misma dirección,
		 * podría haber sobreescrito wheel right con wheel left después de comprobar que son iguales al pedir los datos en Car.twoWheels()
		 * en vez de cambiar esta excepción y añadir aquí toString(),
		 * pero entonces estaría haciendo dos veces la misma comprobación*/ 
		

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
