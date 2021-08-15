package N1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import exceptions.WrongPlateException;
import exceptions.WrongWheelsException;

public class WorkshopApp {

	public static void main(String[] args) throws Exception {
		// Se piden datos y se crea registro coche
		Car car = createCar();

		// pide marca y di�metro ruedas
		List<Wheel> frontWheels = twoWheels("delanteras");
		List<Wheel> backWheels = twoWheels("traseras");
		
		//se a�ade info ruedas al registro coche
		try {
			car.addWheels(frontWheels, backWheels); 
		} catch (WrongWheelsException e) {
			JOptionPane.showMessageDialog(null,"RUEDAS NO V�LIDAS", "ERROR", 0);
		} finally {
			System.out.println(car.toString());
		}
	}


	// para pedir datos y crear registro coche
	public static Car createCar() {
		String plate = "";
		while (plate.equals("")) {
			plate = JOptionPane.showInputDialog("Introduce matr�cula coche");
			try {
				checkPlate(plate);
			} catch (WrongPlateException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "M�TRICULA NO V�LIDA", "ERROR", 0);
				plate = "";
			}
		}		
		Car car = new Car(plate, JOptionPane.showInputDialog("Introduce marca coche"),
						JOptionPane.showInputDialog("Introduce color coche"));
		return car;
	}
	
	//para comprobar que la matr�cula es correcta
	public static void checkPlate(String plate) throws WrongPlateException {
		if (plate.length()>7) {
			throw new WrongPlateException("Supera el m�ximo de caracteres permitidos(7)");
		}
		for(int i = 0; i < 4; i++) {
			if (!Character.isDigit(plate.charAt(i))){
				throw new WrongPlateException("Una matr�cula tiene 4 n�meros y 2 � 3 letras");
			}
		}
		String plateLetters = plate.substring(4); 
		for(int i = plateLetters.length()-1; i >= 0; i--) {
			if(Character.isDigit(plateLetters.charAt(i))){
				throw new WrongPlateException("Una matr�cula tiene 4 n�meros y 2 � 3 letras");
			}
		}
	}

	// para pedir info y crear par de ruedas
	public static List<Wheel> twoWheels(String axle) throws WrongWheelsException {
		JOptionPane.showMessageDialog(null, "Ruedas "+axle);
		List<Wheel> twoWheels = new ArrayList<Wheel>();
		twoWheels.add(createWheel(axle, "DERECHA"));
		twoWheels.add(createWheel(axle, "IZQUIERDA"));
		return twoWheels;
	}
	
	//para crear una rueda
	public static Wheel createWheel(String axle, String side) throws WrongWheelsException{
		JOptionPane.showMessageDialog(null, "Ruedas "+axle+" - "+side);
		String brand = JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "MARCA");
		double diameter;
		do {
			diameter = Double.parseDouble(JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "DI�METRO"));
			if(diameter<0.4 || diameter>4){
				JOptionPane.showMessageDialog(null, "Di�metro rueda debe estar entre 0.4 y 4");
			}	
		}while (diameter<0.4 || diameter>4);
		
		Wheel wheel = new Wheel(brand, diameter);
		return wheel;
	}
	
	
}
