/*Crea una aplicació que dibuixi una escala de nombres, sent cada línia nombres començant en un i acabant en el nombre de la línia.*/

package N2;

import java.util.Scanner;

public class EscaleraNumeros {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce un número");
		int numLineas = sc.nextInt();
		
		for(int i = 1; i <= numLineas; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}

	}

}
