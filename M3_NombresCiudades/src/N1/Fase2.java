/*Fase 2
 * Un cop tenim els noms de les ciutats guardats en variables haurem de pasar l’informacio a un array (arrayCiutats).
 * Quan tinguem l’array ple, haurem de mostrar per consola el nom de les ciutats ordenadas per ordre alfabetic. 
*/

package N1;

import java.util.*;

public class Fase2 {

	public static void main(String[] args) {
		ArrayList<String> cities = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		
		for(int i = 1; i <= 6; i++) {
			System.out.println("Introduce la " + i + "ª ciudad:");
			cities.add(sc.nextLine());
		}
				
		System.out.println("Ciudades por orden alfabético:");
		Collections.sort(cities);
		for(String city : cities) {
			System.out.println(city);
		}
	
	}
	
}
