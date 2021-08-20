package N1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class WorkshopApp {

	public static void main(String[] args) {
		//Se elige entre coche o moto
		int type = chooseType();
		boolean fin = false;
		while(!fin) {
			// Se piden datos del vehículo y se crea un registro de vehículo nuevo
			ArrayList<String> infoVehicle = infoVehicle();
			if (!infoVehicle.isEmpty()) {
				switch (type) {
					case 0:
						Car car = new Car(infoVehicle.get(0), infoVehicle.get(1), infoVehicle.get(2));
						List<Wheel> frontWheels = twoWheels("delanteras");
						List<Wheel> backWheels = new ArrayList<Wheel>();
						if(!frontWheels.isEmpty()) {
							backWheels = twoWheels("traseras");
						} else {
							fin = true;
						}
						if(!backWheels.isEmpty()) {
							car.addWheels(frontWheels, backWheels);
						} else {
							fin = true;
						}
						System.out.println(car.toString());
						break;
					case 1:
						Bike bike = new Bike(infoVehicle.get(0), infoVehicle.get(1), infoVehicle.get(2));
						ArrayList infoWheel = infoWheel("delantera");
						if(!infoWheel.isEmpty()) {
							Wheel frontWheel = createWheel(infoWheel);
							bike.addWheels(frontWheel);
							infoWheel = infoWheel("trasera");
							if(!infoWheel.isEmpty()) {
								Wheel backWheel = createWheel(infoWheel);
								bike.addWheels(backWheel);
							}else {
								fin = true;
								bike.wheels.clear();
							}
						}else {
							fin = true;
						}
						System.out.println(bike.toString());
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", 2);
						fin = true;
						break;
				}
			}else {
				fin = true;
			}
		}
	}
	//para elegir tipo de vehículo
	public static int chooseType() {
		String[] buttons = {"Coche", "Moto", "Salir"};
		int type = JOptionPane.showOptionDialog(null, "Tipo de vehículo", "Nuevo vehículo",
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null,
						buttons, buttons[0]);
		return type;
	}

	// para pedir datos del vehículo
	public static ArrayList<String> infoVehicle() {
		ArrayList<String> infoVehicle = new ArrayList<String>();
		String plate = "";
		while (plate.equals("")) {
			plate = JOptionPane.showInputDialog("Introduce matrícula");
			if(plate == null) {
				JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", 2);
				plate = "cancel";
			} else {
				if(!checkPlate(plate)) {
					plate = "";
				}
			}
		}
		if(!plate.equals("cancel")) {
			infoVehicle.add(plate);
			String brand = JOptionPane.showInputDialog("Introduce marca");
			if(brand == null) {
				infoVehicle.clear();
				JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", 2);
			} else {
				infoVehicle.add(brand);
				String color = JOptionPane.showInputDialog("Introduce color");
				if(color == null) {
					infoVehicle.clear();
					JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", 2);
				}else {
					infoVehicle.add(color);
				}
			}
		}
		return infoVehicle; 
	}
	
	//para comprobar que la matrícula es correcta
	public static boolean checkPlate(String plate)  {
		boolean validPlate = true;
		if (plate.length()>7) {
			validPlate = false;
			JOptionPane.showMessageDialog(null, "MÁTRICULA NO VÁLIDA", "ERROR", 0);
			JOptionPane.showMessageDialog(null, "Máxima longitud matrícula = 7 caracteres", "ERROR", 0);
		}
		if (validPlate) {
			for(int i = 0; i < 4; i++) {
				if (!Character.isDigit(plate.charAt(i))){
					validPlate = false;
					if(!validPlate) {
						JOptionPane.showMessageDialog(null, "MÁTRICULA NO VÁLIDA", "ERROR", 0);
						JOptionPane.showMessageDialog(null, "Una matrícula tiene 4 números y 2 ó 3 letras", "ERROR", 0);
						i = 4;
					}
				}
			}
		}
		if(validPlate) {
			String plateLetters = plate.substring(4); 
			for(int i = plateLetters.length()-1; i >= 0; i--) {
				if(Character.isDigit(plateLetters.charAt(i))){
					validPlate = false;
					if(!validPlate) {
						JOptionPane.showMessageDialog(null, "MÁTRICULA NO VÁLIDA", "ERROR", 0);
						JOptionPane.showMessageDialog(null, "Una matrícula tiene 4 números y 2 ó 3 letras", "ERROR", 0);
						i = 0;
					}
					
				}
			}
		}
		return validPlate;
	}
	
	//para pedir info rueda moto
	public static ArrayList infoWheel(String axle){
		ArrayList infoWheel = new ArrayList<>();
		String diameterString = null;
		double diameter = 0d;
		boolean fin = false;
		JOptionPane.showMessageDialog(null, "Rueda "+axle);
		String brand = JOptionPane.showInputDialog("Rueda "+axle, "MARCA");
		if(brand == null) {
			JOptionPane.showMessageDialog(null, "No se han seleccionado ruedas para esta moto", "RUEDAS CANCELADAS", 2);
		} else {
			infoWheel.add(brand);
			do {
				diameterString = JOptionPane.showInputDialog("Rueda "+axle, "DIÁMETRO");
				if(diameterString != null) {
					diameter = Double.parseDouble(diameterString);
					if(diameter<0.4 || diameter>4){
						JOptionPane.showMessageDialog(null, "Diámetro rueda debe estar entre 0.4 y 4", "ERROR", 0);
					}
					
				}else {
					fin = true;
					infoWheel.clear();
					JOptionPane.showMessageDialog(null, "No se han seleccionado ruedas para esta moto", "RUEDAS CANCELADAS", 2);
				} 
			}while ((diameter<0.4 || diameter>4) && !fin);	
			infoWheel.add(diameter);
			if(fin) {
				infoWheel.clear();
			}
		}

		return infoWheel;
	}
	//para crear rueda moto
		public static Wheel createWheel(ArrayList infoWheel){
			String brand = (String) infoWheel.get(0);
			double diameter = (double) infoWheel.get(1);
			Wheel wheel = new Wheel(brand, diameter);
		
			return wheel;
		}

	//para pedir info rueda coche
	public static ArrayList infoWheel(String axle, String side){
		ArrayList infoWheel = new ArrayList<>();
		String diameterString = "";
		double diameter = 0d;
		boolean fin = false;
		JOptionPane.showMessageDialog(null, "Ruedas "+axle+" - "+side);
		String brand = JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "MARCA");
		if(brand == null) {
			JOptionPane.showMessageDialog(null, "No se han seleccionado ruedas para este coche", "RUEDAS CANCELADAS", 2);
		} else {
			infoWheel.add(brand);
			do {
				diameterString = JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "DIÁMETRO");
				if(diameterString == null) {
					fin = true;
					infoWheel.clear();
					JOptionPane.showMessageDialog(null, "No se han seleccionado ruedas para este coche", "RUEDAS CANCELADAS", 2);
				}else {
					diameter = Double.parseDouble(diameterString);
					if(diameter<0.4 || diameter>4){
						JOptionPane.showMessageDialog(null, "Diámetro rueda debe estar entre 0.4 y 4", "ERROR", 0);
					}
				} 
			}while ((diameter<0.4 || diameter>4) && !fin);	
			infoWheel.add(diameter);
			if(fin) {
				infoWheel.clear();
			}
		}
		return infoWheel;
	}
	
	//para pedir crear una rueda coche
	public static Wheel createWheel(String axle, String side){
		List infoWheel = infoWheel(axle, side);
		Wheel wheel;
		if(!infoWheel.isEmpty()) {
			wheel = new Wheel((String) infoWheel.get(0), (double) infoWheel.get(1));
		}else {
			wheel = null;
		}
		return wheel;
	}	
//		JOptionPane.showMessageDialog(null, "Ruedas "+axle+" - "+side);
//		String brand = JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "MARCA");
//		double diameter = Double.parseDouble(JOptionPane.showInputDialog("Ruedas "+axle+" - "+side, "DIÁMETRO"));
//		if(diameter<0.4 || diameter>4){
//			JOptionPane.showMessageDialog(null, "Diámetro rueda debe estar entre 0.4 y 4", "ERROR", 0);
//			createWheel(axle, side);
//		}	
//		Wheel wheel = new Wheel(brand, diameter);
//	
//		return wheel;
//	}
	// para crear par de ruedas
	public static List<Wheel> twoWheels(String axle) {
		JOptionPane.showMessageDialog(null, "Ruedas "+axle);
		List<Wheel> twoWheels = new ArrayList<Wheel>();
		Wheel wheel = createWheel(axle, "DERECHA");
		if(wheel == null) {
			twoWheels.clear();
		} else {
			twoWheels.add(wheel);
			wheel = createWheel(axle, "IZQUIERDA");
			if(wheel == null) {
				twoWheels.clear();	
			}else {
				twoWheels.add(createWheel(axle, "IZQUIERDA"));
			}
		}
		return twoWheels;
		}
}
