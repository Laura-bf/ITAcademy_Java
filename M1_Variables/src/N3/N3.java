package N3;

public class N3 {

	public static void main(String[] args) {
		
		int[] array = {3,4,5,9,8,7};
		String auxiliar;
		for(int i = 0; i < array.length; i++) {
			auxiliar = array[i] + " ";
			System.out.print(auxiliar);
		}
		System.out.println("\n");	
		for (int i = array.length-1; i >= 0; i--) {
			auxiliar = array[i] +" ";
			System.out.print(auxiliar);
			
		}

	}

}
