package n3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		//se crea un listado de 10 alumnos 
		List<Student> students = new ArrayList<Student>(
				Arrays.asList(new Student("Luis", 20, "PHP", 7), new Student("María", 21, "REACT", 8),
						new Student("Pol", 22, "JAVA", 6), new Student("Lorena", 17, "PHP", 9),
						new Student("Marco", 22, "REACT", 4.5), new Student("Ana", 17, "JAVA", 9),
						new Student("Luz", 22, "REACT", 6), new Student("Jon", 20, "JAVA", 7),
						new Student("Itziar", 22, "PHP", 6), new Student("Andreu", 22, "PHP", 4)));
		
		//1. imprime nombre y edad de cada alumno
		System.out.println("======NOMBRE Y EDAD ALUMNOS======");
		students.stream().forEach(s -> System.out.println(s.getName() + ", " + s.getAge() + " años."));
		
		//2. crea una nueva lista con los alumnos que empiezan por a e imprime esa nueva lista
		System.out.println("\n======LISTA DE ALUMNOS CON A INICIAL======");
		List<Student> aStudents = students.stream().filter(s -> s.getName().charAt(0)=='A').collect(Collectors.toList());
		for(Student s : aStudents)
			System.out.println(s.toString());
		
		//3. filtra y muestra todos los alumnos que tienen un 5 o más de nota
		System.out.println("\n======ALUMNOS APROBADOS======");
		students.stream().filter(s -> s.getGrade()>=5).forEach(System.out::println);
		
		//4. filtra y muestra, de todos los alumnos con 5 o más nota, los que no estudian PHP
		System.out.println("\n======ALUMNOS APROBADOS QUE NO ESTUDIAN PHP======");
		students.stream().filter(s -> s.getGrade()>=5).filter(s -> !s.getCourse().equalsIgnoreCase("PHP")).forEach(System.out::println);
		
		//5. muestra todos los alumnos que estudian java que sean mayores de edad
		System.out.println("\n======ALUMNOS DE JAVA MAYORES DE EDAD======");
		students.stream().filter(s -> s.getCourse().equalsIgnoreCase("JAVA")).filter(s -> s.getAge()>=18).forEach(System.out::println);
	}

}
