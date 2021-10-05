package rockets.view;

import javax.swing.JOptionPane;

import rockets.domain.*;

public class Rockets_APP {

	public static void main(String[] args) {

		String code1 = "32WESSDS";
		Rocket r1 = new Rocket(code1);
		r1.addPropeller(new Propeller(code1 + "-0", 10));
		r1.addPropeller(new Propeller(code1 + "-1", 30));
		r1.addPropeller(new Propeller(code1 + "-2", 80));

		String code2 = "LDSFJA32";
		Rocket r2 = new Rocket(code2);
		r2.addPropeller(new Propeller(code2 + "-0", 30));
		r2.addPropeller(new Propeller(code2 + "-1", 40));
		r2.addPropeller(new Propeller(code2 + "-2", 50));
		r2.addPropeller(new Propeller(code2 + "-3", 50));
		r2.addPropeller(new Propeller(code2 + "-4", 30));
		r2.addPropeller(new Propeller(code2 + "-5", 10));

		// se establece la velocidad objetivo para cada cohete y si cancela, se cierra
		// el programa
		if (!setRocketTargetSpeed(r1) && !setRocketTargetSpeed(r2)) {

			// se establece potencia objetivo para cada propulsor de cada cohete
			setPropTarget(r1);
			setPropTarget(r2);

			new Window(r1, r2);

		} else {
			JOptionPane.showMessageDialog(null, "LANZAMIENTO CANCELADO!!");
		}

	}

	public static boolean setRocketTargetSpeed(Rocket rocket) {
		boolean end = false;
		boolean targetNull = false;
		while (!end) {
			String target = JOptionPane.showInputDialog("Velocidad para cohete " + rocket.getCode());
			if (target != null) {
				if (target.isEmpty()) {
					JOptionPane.showMessageDialog(null, "INTRODUCE UNA VELOCIDAD OBJETIVO!!!");
					end = false;
				} else {
					double targetSpeed = Double.parseDouble(target);
					if ((Math.pow((targetSpeed - rocket.getSpeed()) / 100, 2)) > rocket.getTotalMaxPower()) {
						System.out.println("POTENCIA INSUFICIENTE!! Velocidad imposible de alcanzar");
						JOptionPane.showMessageDialog(null, "POTENCIA INSUFICIENTE - VELOCIDAD IMPOSIBLE!!!");
						end = false;
					} else {
						rocket.setTargetSpeed(targetSpeed);
						end = true;
					}
				}
			} else {
				targetNull = true;
				end = true;
			}
		}
		return targetNull;
	}

	public static void setPropTarget(Rocket rocket) {
		double targetSpeed = rocket.getTargetSpeed();
		double speed = rocket.getSpeed();
		int numProp = rocket.getNumPropellers();
		int powerNeeded = (int) Math.pow((targetSpeed - speed) / 100, 2);
		int powerProp = powerNeeded / numProp;
		int restPower = 0;

		for (Propeller prop : rocket.getPropellers()) {
			if (powerProp <= prop.getMaxPower())
				prop.setTargetPower(powerProp);
			else {
				prop.setTargetPower(prop.getMaxPower());
				numProp -= 1;
				restPower += powerProp - prop.getMaxPower();
			}
		}
		int rest = restPower / rocket.getNumPropellers();
		while (restPower > 0) {
			for (Propeller prop : rocket.getPropellers()) {
				if (prop.getMaxPower() != prop.getTargetPower()) {
					if (prop.getMaxPower() - prop.getTargetPower() >= rest) {
						int targetPow = prop.getTargetPower() + rest;
						prop.setTargetPower(targetPow);
						restPower -= rest;
					} else {
						int powerLeft = prop.getMaxPower() - prop.getTargetPower();
						int targetPow = prop.getTargetPower() + powerLeft;
						prop.setTargetPower(targetPow);
						restPower -= powerLeft;
					}
				}
			}
		}
	}

}
