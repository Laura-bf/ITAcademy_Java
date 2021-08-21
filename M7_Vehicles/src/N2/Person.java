package N2;

import java.util.Date;

public abstract class Person {
	/**
	 * Nombre
	 */
	protected String name;
	/**
	 * Apellido
	 */
	protected String surname;
	/**
	 * Fecha de nacimiento
	 */
	protected Date birthDate;
	
	/**
	 * Constructor por defecto
	 */
	public Person() {
	}
	
	/**
	 * Constructor con 3 parámetros
	 * @param name
	 * @param surname
	 * @param birthDate
	 */
	public Person(String name, String surname, Date birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Person\nName: " + name + ", Surname: " + surname + ", BirthDate: " + birthDate;
	}
	
}
