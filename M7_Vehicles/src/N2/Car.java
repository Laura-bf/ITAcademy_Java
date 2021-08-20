package N2;

import java.util.List;

public class Car extends Vehicle {
	/**
	 * Constante tipo de carnet de conducir que requiere
	 */
	private final static char LICENSE = 'B';
	
	/**
	 * Constructor con 3 parámetros
	 * @param plate
	 * @param brand
	 * @param color
	 */
	public Car(String plate, String brand, String color) {
		super(plate, brand, color);
	}
	
	/**
	 * Devuelve el tipo de licencia para coche
	 * @return carnet de conducir B
	 */
	public static char getLicense() {
		return LICENSE;
	}
	
	/**
	 * Añade al registro coche dos pares de ruedas: delanteras y traseras
	 * @param frontWheels (ruedas delanteras derecha e izquierda)
	 * @param backWheels (ruedas traseras derecha e izquierda)
	 */
	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels){
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}
	
	/**
	 * Crea un par de ruedas para luego añadirlo al registro del coche
	 * Comprueba que sean dos ruedas por cada eje
	 * Comprueba que sean las dos ruedas iguales
	 * @param wheels
	 */
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
		return "Car:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels;
	}


		

}
