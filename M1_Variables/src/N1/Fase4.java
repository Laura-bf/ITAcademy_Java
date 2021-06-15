package N1;

import java.util.Scanner;

public class Fase4 {

	public static void main(String[] args) {
		String nom     = "Laura";
		String cognom1 = "Benito";
		String cognom2 = "Fernández";
		
		String nomComplet = nom +" "+ cognom1 +" "+ cognom2;
		System.out.println("Mi nombre es "+ nomComplet);
				
		int dia = 28;
		int mes = 5;
		int any = 1981;
		
		String dataNaixement = dia +"/"+ mes +"/"+ any;
		System.out.println("Nací el "+ dataNaixement);
		
		int leapYear       = 1948;
		final int INTERVAL = 4;
		int birthYear      = any;
		boolean isLeapYear = true;
		
		for(int i = leapYear; i <= birthYear; i+=INTERVAL) {
			leapYear = i;
		}
		if(leapYear != birthYear) {
			isLeapYear = false;		
		}
		
		String message1 = "El año "+ birthYear +" es bisiesto.";
		String message2 = "El año "+ birthYear +" no es bisiesto.";
		
		if(isLeapYear) {
			System.out.println(message1);
		} else {
			System.out.println(message2);
		}
		
	}

}
