package N1;

import java.util.ArrayList;

public class Fase4 {

	public static void main(String[] args) {
		ArrayList<Character> nombre = new ArrayList<Character>();
		nombre.add('l');
		nombre.add('a');
		nombre.add('u');
		nombre.add('r');
		nombre.add('a');
		
		ArrayList<Character> apellido = new ArrayList<Character>();
		apellido.add('b');
		apellido.add('e');
		apellido.add('n');
		apellido.add('i');
		apellido.add('t');
		apellido.add('o');
		
		ArrayList<Character> nombreCompleto = new ArrayList<Character>();
		nombreCompleto.addAll(nombre);
		nombreCompleto.add(' ');
		nombreCompleto.addAll(apellido);
		
		System.out.println("Nombre Completo: "+ nombreCompleto.toString());
		
	}

}
