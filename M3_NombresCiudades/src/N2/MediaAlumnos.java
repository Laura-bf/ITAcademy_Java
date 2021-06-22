/*En aquest exercici crearàs un array bidimensional on introduiràs 3 notes per a 5 alumnes. 
 * Es calcularà la nota mitjana de cada alumna i s'indicarà si han suspès o no.
 * Crea un array bidimensional capaç de guardar 3 notes de 5 alumnes.
 * Omple l’array amb un bucle demanant els valors per pantalla (nota entre 0 i 10), has d’identificar per pantalla quan és una agrupació d’un alumne nou.
 * Recorre l'array, fes la Mitjana aritmètica de les 3 notes i indica si l'alumne ha aprovat o suspès */

package N2;

import java.util.Scanner;

public class MediaAlumnos {

	public static void main(String[] args) {
		int[][] notasClase = new int[3][5];
		Scanner sc = new Scanner(System.in);
				
		//Se introducen las notas de cada alumno
		for(int i = 0; i < notasClase[0].length; i++) {
			System.out.println("Notas del alumno "+ (i+1));
			for(int j = 0; j < notasClase.length; j++) {
				int nota;
				do {
					System.out.println("Introduce la nota "+ (j+1) +" para el alumno "+ (i+1));
					nota = sc.nextInt();
				} while (nota < 0 || nota >10);
				notasClase[j][i] = nota;
			}
		}
		//Indica si el alumno ha aprobado después de calcular la media
		for (int i = 0; i < notasClase[0].length; i++){
			int totalNotas = 0;
			for(int j = 0; j < notasClase.length; j++) {
				totalNotas += notasClase[j][i];
			}
			double media = totalNotas/notasClase.length;
			if(media >= 5) {
				System.out.println("El alumno " + i + " ha aprobado con un " + media);
			} else {
				System.out.println("El alumno " + i + " ha suspendido con un " + media);
			}
		
		}
	}


}
