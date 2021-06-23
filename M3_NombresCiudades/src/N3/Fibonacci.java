/*Escriviu un programa que donat un numero N retorni la seqüència de Fibonacci fins al numero N.
 * La seqüència de Fibonacci és la sèrie de nombres: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ... 
 * El número següent es troba sumant els dos números anteriors. 
*/
package N3;

import java.util.*;

public class Fibonacci {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> fibonacci = new ArrayList<Integer>();
				
		System.out.println("Introduce un número para la secuencia de Fibonacci");
		int number = sc.nextInt();
		
		//se añaden valores al arraylist según el número introducido
		for(int i = 0; i < number; i++) {
			if(i == 0) {
				fibonacci.add(i);
			} else if(i == 1) {
				fibonacci.add(i);
			} else {
				int next = fibonacci.get(i-1) + fibonacci.get(i-2);
				fibonacci.add(next);
			}
		}
		//se imprimen los valores del arraylist
		for(int value : fibonacci) {
			System.out.print(value + " ");
		}

	}

}
