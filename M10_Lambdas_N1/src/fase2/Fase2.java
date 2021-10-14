package fase2;

public class Fase2 {

	public static void main(String[] args) {
		
		//instancia la interface asignándole una lambda compatible con su método
		IFase2 pi = () -> 3.1415;
		
		//imprime el resultado de llamar al método de la instancia de la interfaz funcional
		System.out.println(pi.getPiValue());

	}

}
