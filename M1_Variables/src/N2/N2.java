package N2;

public class N2 {

	public static void main(String[] args) {
		double doubleVar = 3.1416;
		int  intVar;
		float floatVar;
		String stringVar;
		
		intVar = (int) doubleVar;
		floatVar = (float) doubleVar;
		stringVar = Double.toString(doubleVar);
		
		System.out.println(doubleVar + " double");
		System.out.println(intVar +" int");
		System.out.println(floatVar +" float");
		System.out.println(stringVar +" String");
	}

}
