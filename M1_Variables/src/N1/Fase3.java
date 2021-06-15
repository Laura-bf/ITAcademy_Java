package N1;

public class Fase3 {

	public static void main(String[] args) {
		int leapYear       = 1948;
		final int INTERVAL = 4;
		int birthYear      = 1981;
		boolean isLeapYear = true;
		
		for(int i = leapYear; i <= birthYear; i+=INTERVAL) {
			leapYear = i;
		}
		if(leapYear != birthYear) {
			isLeapYear = false;		
		}
		
		String message1 = "El año "+ birthYear +" es bisiesto.";
		String message2 = "El año "+ birthYear +" no es bisiesto.";
		
		if(isLeapYear) {
			System.out.println(message1);
		} else {
			System.out.println(message2);
		}
		
	}

}
