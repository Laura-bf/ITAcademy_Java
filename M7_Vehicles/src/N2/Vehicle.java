package N2;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	/**
	 * Matr�cula del veh�culo 
	 */
	protected String plate;
	/**
	 * Marca del veh�culo
	 */
	protected String brand;
	/**
	 * Color del veh�culo
	 */
	protected String color;
	/**
	 * Ruedas del veh�culo
	 */
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	
	/**
	 * Constructor con 3 par�metros
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
	 * Devuelve la marca del veh�culo
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Devuelve el color de un veh�culo
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Devuelve la lista con las ruedas que tiene un veh�culo
	 * @return List<Wheel>
	 */
	public List<Wheel> getWheels() {
		return wheels;
	}

	/**
	 * Devuelve la matr�cula de un veh�culo
	 * @return plate
	 */
	public String getPlate() {
		return plate;
	}
	
	/**
	 * M�todo abstracto para a�adir un par de ruedas seg�n tipo veh�culo
	 * @param wheels (par de ruedas seg�n tipo veh�culo)
	 */
	public abstract void addTwoWheels(List<Wheel> wheels);

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + "]";
	}

	
}
