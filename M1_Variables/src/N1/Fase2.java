package N1;

public class Fase2 {

	public static void main(String[] args) {
		int leapYear       = 1948;
		final int INTERVAL = 4;
		int birthYear      = 1981;
		
		int numLeapYears = 1 + (birthYear - leapYear) / INTERVAL;
		System.out.println("Número de años bisiestos desde 1948 hasta 1981: "+ numLeapYears +"\n");
		
		System.out.println("Años bisiestos desde 1948 hasta 1981:");
		for(int i = leapYear; i <= birthYear; i+=4) {
			System.out.print(i +" ");
		}
		
	}

}
