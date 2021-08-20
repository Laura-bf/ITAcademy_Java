package N2;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	/**
	 * Matrícula del vehículo 
	 */
	protected String plate;
	/**
	 * Marca del vehículo
	 */
	protected String brand;
	/**
	 * Color del vehículo
	 */
	protected String color;
	/**
	 * Ruedas del vehículo
	 */
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	
	/**
	 * Constructor con 3 parámetros
	 * @param plate
	 * @param brand
	 * @param color
	 */
	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
	/**
	 * Devuelve la marca del vehículo
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Devuelve el color de un vehículo
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Devuelve la lista con las ruedas que tiene un vehículo
	 * @return List<Wheel>
	 */
	public List<Wheel> getWheels() {
		return wheels;
	}

	/**
	 * Devuelve la matrícula de un vehículo
	 * @return plate
	 */
	public String getPlate() {
		return plate;
	}
	
	/**
	 * Método abstracto para añadir un par de ruedas según tipo vehículo
	 * @param wheels (par de ruedas según tipo vehículo)
	 */
	public abstract void addTwoWheels(List<Wheel> wheels);

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + "]";
	}

	
}
