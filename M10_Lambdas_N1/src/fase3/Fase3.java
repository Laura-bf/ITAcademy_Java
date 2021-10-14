package fase3;

public class Fase3 {

	public static void main(String[] args) {
	
		IFase3 invString = s -> {
			String iS ="";
			for(int i=s.length()-1;i>=0; i--) {
				iS += s.charAt(i);
			}
			return iS;
		};

		System.out.println(invString.reverse("Hola qué tal todo?"));
	}

}
