package fase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Fase1 {

	public static void main(String[] args) {

		List<String> names = new ArrayList<String>(Arrays.asList("Luis", "María", "Pol", "Lorena", "Marco", "Ana", "Luz", "Jon", "Itziar", "Andreu"));
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(11, 22, 15, 6, 78, 45, 97, 102, 34, 51));
		
		//1.	
		System.out.println("==========EMPIEZAN POR \"A\" Y TIENEN 3 LETRAS===========");
		startLetter(names,"a",3).forEach(n -> System.out.println(n)); //con lambda
//		startLetter(names,"a",3).forEach(System.out::println); //con método de referencia
		
		//2.
		System.out.println("\n==========NÚMEROS A UN STRING===========");
		System.out.println(numbersToString(numbers));
		
		//3.
		System.out.println("\n==========TIENEN UNA \"O\"===========");
		containsLetter(names, "o").forEach(n -> System.out.println(n));
//		containsLetter(names, "o").forEach(System.out::println);
		
		//4.
		System.out.println("\n==========TIENEN UNA \"O\" Y TIENEN MÁS DE 5 LETRAS===========");
		letterAndLenght(names, "o", 5).forEach(n -> System.out.println(n));
//		letterAndLenght(names, "o", 5).forEach(System.out::println);
		
		List<String> months = new ArrayList<String>(Arrays.asList("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" ));
		
		//5.Imprime listado meses usando una lambda
		System.out.println("\n==========MESES USANDO LAMBDA===========");
		months.forEach(m -> System.out.println(m));
		
		//6.Imprime listado meses usando método de referencia
		System.out.println("\n==========MESES USANDO MÉTODO REFERENCIA===========");
		months.forEach(System.out::println);
		
		
	}
//1. Devuelve listado con los strings de una lista que comienzan por una letra mayúscula dada y con una longitud determinada	
	private static List<String> startLetter(List<String> list, String letter, int length){
		return list.stream().filter(l -> l.startsWith(letter.toUpperCase())).filter(l -> l.length()==length).collect(Collectors.toList()); 
	}
//2. Convierte una lista de enteros en un único string, primero les añade 'o'/'e' según sea par o impar y luego los une separados por comas 	
	private static String numbersToString(List<Integer> list){
		return list.stream().map(l -> (l%2==0) ? "e"+l : "o"+l).collect(Collectors.joining(","));
	}
//3. Devuelve listado con todos los string de una lista que contengan una letra dada
	private static List<String> containsLetter(List<String> list, String letter){
		return list.stream().filter(l -> l.contains(letter)).collect(Collectors.toList());
	}
//4. Devuelve listado con todos los strings de una lista que contengan un letra dada y con más de un número dado de letras	
	private static List<String> letterAndLenght(List<String> list, String letter, int length){
		return list.stream().filter(l -> l.contains(letter)).filter(l -> l.length()>length).collect(Collectors.toList());
	}
}
