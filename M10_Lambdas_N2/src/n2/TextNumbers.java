package n2;

import java.util.ArrayList;				//??????????por qu� tengo que hacer cast a string en l�nea 20 y en la 27?????????????
import java.util.Arrays;				//?????l�nea 24 ==> NO SE PUEDE CON M�TODO REFERENCIA?????
import java.util.Comparator;
import java.util.List;

public class TextNumbers {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>(Arrays.asList("3u1s", "efgh789", "M7r�0", "P25ol", "Lo8r9ena", "247arco", "A24na", "L123uz", "1122", "Syo15","7dfr96","5454545"));
		
		
		System.out.println("======ORDEN POR LONGITUD DE MENOR A MAYOR============");
		list.stream().sorted(Comparator.comparing(s -> s.length())).forEach(System.out::println);
//		list.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
		
		System.out.println("======ORDEN POR LONGITUD DE MAYOR A MENOR============");
		list.stream().sorted(Comparator.comparing(s -> ((String) s).length()).reversed()).forEach(System.out::println);
//		list.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
		
		System.out.println("======ALFAB�TICAMENTE POR EL PRIMER CARACTER============");
		list.stream().sorted(Comparator.comparing(s -> s.charAt(0))).forEach(System.out::println);

		System.out.println("======PRIMERO LAS QUE EMPIEZAN POR \"E\"============");
		list.stream().sorted(Comparator.comparing(s -> ((String) s).charAt(0)=='e').reversed()).forEach(System.out::println);
		
		System.out.println("======CAMBIA \"a\" POR \"4\"============");
		list.stream().map(s -> s.replace('a', '4')).forEach(System.out::println);
		
		System.out.println("======CAMBIA \"a\" y \"A\" POR \"4\"============");
		//si tambi�n quiero cambiar las A may�sculas mapeo dos veces en lugar de cambiar todo a may�sculas o min�sculas porque as� hago no modifico el resto de chars
		list.stream().map(s -> s.replace('a', 'A')).map(s -> s.replace('A', '4')).forEach(System.out::println);

		System.out.println("======S�LO LOS 100% NUM�RICOS============");
		list.stream().filter(s -> allDigits(s)==true).forEach(System.out::println);

//*********** OPERACIONES CON CALCULADORA ***************
		ICalculadora sum = (x,y) -> x + y;
		ICalculadora rest = (x,y) -> x - y;
		ICalculadora mult = (x,y) -> x*y;
		ICalculadora div = (x,y) -> x/y;
		
		System.out.println("3 + 5 = " + sum.operation(3, 5));
		System.out.println("7 - 2,5 = " + rest.operation(7, 2.5f));
		System.out.println("5,25 * 10 = " + mult.operation(5.25f, 10));
		System.out.println("25 / 2 = " + div.operation(25, 2));
	}
	
	
	//para comprobar si todos los caracteres del string son n�meros
	private static boolean allDigits(String s) {
		boolean digit = true;
		for(int i=0; i<s.length(); i++) {
			if(Character.isDigit(s.charAt(i)))
				digit = true;
			else {
				digit = false;
				i=s.length();
			}
		}
		return digit;
	}
}
