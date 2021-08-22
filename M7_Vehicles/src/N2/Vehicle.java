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
	 * Titular del veh�culo
	 */
	protected Owner owner;
	/**
	 * Conductor del veh�culo
	 */
	protected Driver driver;
	
	/**
	 * Constructor por defecto
	 */
	public Vehicle() {
	}
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
	 * Modifica la marca de un veh�culo
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * Devuelve el color de un veh�culo
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Modifica el color de un veh�culo
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * Devuelve la matr�cula de un veh�culo
	 * @return plate
	 */
	public String getPlate() {
		return plate;
	}
	/**
	 * Modifica la matr�cula de un veh�culo
	 * @param plate
	 */
	public void setPlate(String plate) {
		if(checkPlate(plate)) {
			this.plate = plate;
		}
	}
	/**
	 * Devuelve la lista con las ruedas que tiene un veh�culo
	 * @return List<Wheel>
	 */
	public List<Wheel> getWheels() {
		return wheels;
	}
	
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	/**
	 * M�todo para comprobar que la matr�cula es v�lida
	 * @param plate
	 */
	public boolean checkPlate(String plate) {
		boolean validPlate = true;
		if(plate.length()>7 || plate.length()<6) {
			System.err.println("M�tricula no v�lida. Obligatorio: 6 � 7 caracteres");
			validPlate = false;
		} else {
			for(int i=0; i<4; i++) {
				if(!Character.isDigit(plate.charAt(i))) {
					System.err.println("M�tricula no v�lida. Obligatorio: 4 n�meros + 2 � 3 letras");
					i=4;
					validPlate = false;
				} 
			}
			if(validPlate) {
				String plateLetters = plate.substring(4);
				for(int i=plateLetters.length()-1; i>=0; i--) {
					if(Character.isDigit(plateLetters.charAt(i))){
						System.err.println("M�tricula no v�lida. Obligatorio: 4 n�meros + 2 � 3 letras");
						i=0;
						validPlate = false;
					}		
				}
			}
		}
		return validPlate;
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
