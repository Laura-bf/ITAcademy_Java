/*Fase 4
 * Creeu un nou array de caràcters (char array[])per cada una de les ciutats que tenim. La mida dels nous arrays sera la llargada de cada string ( string nomCiutat.Length).  
 * Ompliu els nous arrays lletra per lletra. 
 * Mostreu per consola els nous arrays amb els noms invertits (Ex: Barcelona - anolecraB).*/

package N1;

import java.util.*;

public class Fase4 {

	public static void main(String[] args) {
		//éstas son las ciudades que tenemos:
		String city1 = "Barcelona";
		String city2 = "Berlín";
		String city3 = "Lisboa";
		String city4 = "Madrid";
		String city5 = "Nápoles";
		String city6 = "Roma";
		
		//se crea un nuevo array de char para cada una de las ciudades
		char[] barcelona = new char[city1.length()];
		char[] berlin = new char[city2.length()];
		char[] lisboa = new char[city3.length()];
		char[] madrid = new char[city4.length()];
		char[] napoles = new char[city5.length()];
		char[] roma = new char[city6.length()];
		
		//se rellenan los nuevos arrays letra por letra usando un método
		addLetters(barcelona, city1);
		addLetters(berlin, city2);
		addLetters(lisboa, city3);
		addLetters(madrid, city4);
		addLetters(napoles, city5);
		addLetters(roma, city6);
		
		//se muestran los nuevos arrays y sus nombres invertidos
		System.out.println("Ciudades con los nombres invertidos:");
		texto(barcelona, inverseName(barcelona));
		texto(berlin, inverseName(berlin));
		texto(lisboa, inverseName(lisboa));
		texto(madrid, inverseName(madrid));
		texto(napoles, inverseName(napoles));
		texto(roma, inverseName(roma));

		
	}
	
	//para añadir las letras a los arrays
	public static void addLetters(char[] cityName, String cityNum) {
		for(int i = 0; i < cityName.length; i++) {
			cityName[i] = cityNum.charAt(i);
		}
	}
	
	//para invertir nombre:
	public static String inverseName(char[] cityName) {
		String inverse = "";
		for (int i = cityName.length-1; i >= 0; i--) {
			inverse += cityName[i];
		}
		return inverse;
	}

	//para imprimir texto con par nombre-nombre invertido
	public static void texto(char[] cityName, String inverse) {
		for(char letra : cityName) {
			System.out.print(letra);
		}
		System.out.println(" - " + inverse);
	}
}
