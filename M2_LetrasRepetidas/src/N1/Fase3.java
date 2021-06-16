package N1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fase3 {

	public static void main(String[] args) {
		ArrayList<Character> nombre = new ArrayList<Character>();
		nombre.add('l');
		nombre.add('a');
		nombre.add('u');
		nombre.add('r');
		nombre.add('a');
		
		HashMap<Character, Integer> cuentaLetras = new HashMap<Character, Integer>();
		
		for(int i = 0; i < nombre.size(); i++) {
			int cuenta = 0;
			char letra = nombre.get(i);
			for(int j = i; j < nombre.size(); j++) {
				if(letra == nombre.get(j)) {
					cuenta ++;
				}
			}
			if(!cuentaLetras.containsKey(nombre.get(i))) {
				cuentaLetras.put(nombre.get(i), cuenta);
			}
		}
		
		for (HashMap.Entry<Character, Integer> entry : cuentaLetras.entrySet()) {
			System.out.println("Clave = "+ entry.getKey() +", Valor = "+ entry.getValue());
		}

	}
}
