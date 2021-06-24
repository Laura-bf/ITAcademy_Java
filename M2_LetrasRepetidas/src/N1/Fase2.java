package N1;

import java.util.ArrayList;

public class Fase2 {

	public static void main(String[] args) {
		ArrayList<Character> nombre = new ArrayList<Character>();
		nombre.add('l');
		nombre.add('a');
		nombre.add('u');
		nombre.add('r');
		nombre.add('a');
		nombre.add('6');//para probar si funciona con los n�meros
		nombre.add(' ');
		nombre.add('b');
		nombre.add('e');
		nombre.add('n');
		nombre.add('i');
		nombre.add('t');
		nombre.add('o');

		for(int i = 0; i < nombre.size(); i++) {
			if(nombre.get(i) == ' ') {
				System.out.println();
			} else if(isVocal(nombre.get(i))){
				System.out.println(nombre.get(i) +" VOCAL");
			} else if(isNumber(nombre.get(i))){
				System.out.println(nombre.get(i) +"? Los nombres de personas no contienen n�meros!");
			} else {
				System.out.println(nombre.get(i) +" CONSONANTE");
			}
			
		}
	}
	
	public static boolean isVocal(char letra) {
		boolean isVocal = false;
		if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
			isVocal = true;
		}
		return isVocal;
	}
	public static boolean isNumber(char letra) {
		boolean isNumber = false;
		if(letra >= 48 && letra <= 57) {
			isNumber = true;
		}
		return isNumber;
	}
	/*Existe un m�todo para determinar si un valor char es un d�gito:
	*	SINTAXIS: boolean isDigit(char ch)
	*En este ejercicio:
	*	if(Character.isDigit(nombre.get(i)){
	*		System.out.println(nombre.get(i) +"?Los nombres no contienen n�meros")
	*/
}
