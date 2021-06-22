/*Fase 1
 * Crea sis variables tipu string buides. 
 * Demana per consola que s’introdueixin els noms.  
 * Introdueix els següents noms de ciutats (Barcelona, Madrid, Valencia, Malaga, Cadis, Santander) per teclat.
 * Mostra per consola el nom de les 6 ciutats. */

package N1;

import java.util.*;

public class Fase1 {

public static void main(String[] args) {
	String city1, city2, city3, city4, city5, city6;
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Introduce el nombre de 6 ciudades");
	city1 = sc.nextLine();
	city2 = sc.nextLine();
	city3 = sc.nextLine();
	city4 = sc.nextLine();
	city5 = sc.nextLine();
	city6 = sc.nextLine();
	
	System.out.println(city1 + ", " + city2 + ", " + city3 + ", " + city4 + ", " + city5 + ", " + city6);

}

}
