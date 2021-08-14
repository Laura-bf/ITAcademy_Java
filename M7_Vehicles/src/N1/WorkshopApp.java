package N1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class WorkshopApp {

	public static void main(String[] args) {
		// Se piden datos y se crea registro coche
		Car car = createCar();

		// pide marca y di�metro ruedas
		JOptionPane.showMessageDialog(null, "Ruedas delanteras");
		List<Wheel> frontWheels = twoWheels();
		JOptionPane.showMessageDialog(null, "Ruedas traseras");
		List<Wheel> backWheels = twoWheels();
		
		//se a�ade info ruedas al registro coche
		try {
			car.addWheels(frontWheels, backWheels);
		}catch (Exception e) {
			System.err.println("Deben ser dos ruedas iguales por cada eje (delantero y trasero)");
		}
		
		System.out.println(car);
	}

	// para pedir datos y crear registro coche
	public static Car createCar() {
		Car car = new Car(JOptionPane.showInputDialog("Introduce matr�cula coche"),
				JOptionPane.showInputDialog("Introduce marca coche"),
				JOptionPane.showInputDialog("Introduce color coche"));
		return car;
	}

	// para pedir info ruedas
	public static List<Wheel> twoWheels() {
		List<Wheel> twoWheels = new ArrayList<Wheel>();
		Wheel right = new Wheel(JOptionPane.showInputDialog("Rueda DERECHA", "MARCA"),
							Double.parseDouble(JOptionPane.showInputDialog("Rueda DERECHA", "DI�METRO")));
		twoWheels.add(right);
		Wheel left = new Wheel(JOptionPane.showInputDialog("Rueda IZQUIERDA", "MARCA"),
							Double.parseDouble(JOptionPane.showInputDialog("Rueda IZQUIERDA", "DI�METRO")));
		twoWheels.add(left);
		return twoWheels;

	}
}
