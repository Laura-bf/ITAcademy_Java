package N2;

import java.util.Scanner;
/*Crea una aplicació que dibuixi una piràmide invertida de asteriscs. */

public class PiramideAsteriscos {

	public static void main(String[] args) {
		Scanner sc     = new Scanner(System.in);
		System.out.println("Introduce un número");
		int altura     = sc.nextInt();
		char item      = '*';
		String espacio = "";
		String dibujo  = "";
		
		for(int i = altura; i > 0; i--) {
			for(int j = 1; j <= i; j++) {
				dibujo += item;				
			}
			for(int k = 1; k <= i-1; k++) {
				dibujo += item;
			}
			espacio += " ";
			dibujo += "\n" + espacio;
		}
		
		System.out.println(dibujo);

	}

}
