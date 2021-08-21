package N2;

public class Wheel {
	/**
	 * Marca de la rueda
	 */
	private String brand;
	/**
	 * Diámetro de la rueda
	 */
	private double diameter;
	
	/**
	 * Constructor por defecto
	 */
	public Wheel() {
		
	}
	/**
	 * Constructor con 2 parámetros
	 * @param brand
	 * @param diameter
	 */
	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}
	/**
	 * Devuelve la marca de la rueda
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * Modifica la marca de la rueda
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * Devuelve el diámetro de la rueda
	 * @return diameter
	 */
	public double getDiameter() {
		return diameter;
	}
	/**
	 * Modifica el diámetro de la rueda
	 * Comprueba primero que las medidas están dentro del rango permitido
	 * @param diameter
	 */
	public void setDiameter(double diameter) {
		if (checkDiameter(diameter)) {
			this.diameter = diameter;
		} else {
			System.err.println("ERROR - Diámetro permitido entre 0.4 y 4");
		}
	}
	/**
	 * Comprueba el diámetro de la rueda
	 * @param diameter
	 * @return <ul>
	 * 			<li>true: el diámetro tiene las medidas permitidas
	 * 			<li>false: el diámetro rueda no es válido
	 * 			</ul>
	 */
	public boolean checkDiameter(double diameter) {
		if (diameter > 0.4 && diameter < 4) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Wheel: brand=" + brand + ", diameter=" + diameter;
	}
	
}
