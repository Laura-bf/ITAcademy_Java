package N2;

public class Wheel {
	/**
	 * Marca de la rueda
	 */
	private String brand;
	/**
	 * Di�metro de la rueda
	 */
	private double diameter;
	
	/**
	 * Constructor por defecto
	 */
	public Wheel() {
		
	}
	/**
	 * Constructor con 2 par�metros
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
	 * Devuelve el di�metro de la rueda
	 * @return diameter
	 */
	public double getDiameter() {
		return diameter;
	}
	/**
	 * Modifica el di�metro de la rueda
	 * Comprueba primero que las medidas est�n dentro del rango permitido
	 * @param diameter
	 */
	public void setDiameter(double diameter) {
		if (checkDiameter(diameter)) {
			this.diameter = diameter;
		} else {
			System.err.println("ERROR - Di�metro permitido entre 0.4 y 4");
		}
	}
	/**
	 * Comprueba el di�metro de la rueda
	 * @param diameter
	 * @return <ul>
	 * 			<li>true: el di�metro tiene las medidas permitidas
	 * 			<li>false: el di�metro rueda no es v�lido
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
