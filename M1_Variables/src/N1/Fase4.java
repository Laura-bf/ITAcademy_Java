package N1;

import java.util.Scanner;

public class Fase4 {

	public static void main(String[] args) {
		String nom     = "Laura";
		String cognom1 = "Benito";
		String cognom2 = "Fern�ndez";
		
		String nomComplet = nom +" "+ cognom1 +" "+ cognom2;
		System.out.println("Mi nombre es "+ nomComplet);
				
		int dia = 28;
		int mes = 5;
		int any = 1981;
		
		String dataNaixement = dia +"/"+ mes +"/"+ any;
		System.out.println("Nac� el "+ dataNaixement);
		
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
		
		String message1 = "El a�o "+ birthYear +" es bisiesto.";
		String message2 = "El a�o "+ birthYear +" no es bisiesto.";
		
		if(isLeapYear) {
			System.out.println(message1);
		} else {
			System.out.println(message2);
		}
		
	}

}
