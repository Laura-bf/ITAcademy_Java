package fase2;

public class Fase2 {

	public static void main(String[] args) {
		
		//instancia la interface asign�ndole una lambda compatible con su m�todo
		IFase2 pi = () -> 3.1415;
		
		//imprime el resultado de llamar al m�todo de la instancia de la interfaz funcional
		System.out.println(pi.getPiValue());

	}

}
