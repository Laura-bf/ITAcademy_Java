/*Fase 3
 * Cambieu les vocals �a� dels noms de les ciutats per el numero 4 i guardeu els noms modificats en un nou array(ArrayCiutatsModificades). 
 * Mostreu per consola l�array modificat i ordenat per ordre alfabetic.*/

package N1;

import java.util.*;

public class Fase3 {

	public static void main(String[] args) {
		//para no tener que introducir de nuevo todas las ciudades, a�ado los nombres directamente al ArrayList
		//la recogida de datos ya se complet� en Fase1 y Fase2
		
		ArrayList<String> cities = new ArrayList<String>();
		cities.add("Berl�n");
		cities.add("Madrid");
		cities.add("Lisboa");
		cities.add("Barcelona");
		cities.add("Roma");
		cities.add("N�poles");
		
		ArrayList<String> modifCities = new ArrayList<String>();
		System.out.println("Ciudades modificadas por orden alfab�tico:");
		Collections.sort(cities);
		for(String city : cities) {
				city = city.replace('�', 'a');
				city = city.replace('a', '4');
				modifCities.add(city);
				System.out.println(city);
		}
	
		

	}

}
