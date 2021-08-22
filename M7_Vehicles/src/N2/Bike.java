package N2;

import java.util.List;

public class Bike extends Vehicle {
	/**
	 * Constante tipo de carnet de conducir que requiere 1=A
	 */
	private final static int LICENSE = 1;
	
	/**
	 * Constructor por defecto
	 */
	public Bike() {
	}
	/**
	 * Constructor con 3 par�metros
	 * @param plate
	 * @param brand
	 * @param color
	 */
	public Bike(String plate, String brand, String color) {
		super(plate, brand, color);
	}
	
	/**
	 * Devuelve el tipo de licencia para moto
	 * @return carnet de conducir A
	 */
	public static int getLicense() {
		return LICENSE;
	}
	
	/**
	 * Implementa el m�todo abstracto de la clase Veh�culo para a�adir un par de ruedas
	 * @param wheels (lista de dos ruedas)
	 */
	public void addTwoWheels(List<Wheel> wheels) {
		this.wheels.add(wheels.get(0));
		this.wheels.add(wheels.get(1));
	}
	/**
	 * A�ade al registro moto dos ruedas: delantera y trasera
	 * @param frontWheel (rueda delantera)
	 * @param backWheel (rueda trasera)
	 */
	public void addWheels(Wheel frontWheel, Wheel backWheel) {
		this.wheels.add(frontWheel);
		this.wheels.add(backWheel);
	}
	
	@Override
	public String toString() {
		return "Bike:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels + "\nOwner:\n"+ owner + "\nDriver:\n" + driver;
	}
}