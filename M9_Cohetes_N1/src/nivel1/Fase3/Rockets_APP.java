package nivel1.Fase3;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rockets_APP {
	
	public static void main(String[] args) {
		
		String code1 = "32WESSDS";
		Rocket r1 = new Rocket(code1);
		r1.addPropeller(new Propeller(code1+"-0", 10));
		r1.addPropeller(new Propeller(code1+"-1", 30));
		r1.addPropeller(new Propeller(code1+"-2", 80));
		
		String code2 = "LDSFJA32";
		Rocket r2 = new Rocket(code2);
		r2.addPropeller(new Propeller(code2+"-0", 30));
		r2.addPropeller(new Propeller(code2+"-1", 40));
		r2.addPropeller(new Propeller(code2+"-2", 50));
		r2.addPropeller(new Propeller(code2+"-3", 50));
		r2.addPropeller(new Propeller(code2+"-4", 30));
		r2.addPropeller(new Propeller(code2+"-5", 10));
		
//		System.out.println(r1);
//		System.out.println(r2);
		
		//se establece potencia objetivo para cada propulsor de cada cohete
		setPropTarget(r1.getPropellers());
		setPropTarget(r2.getPropellers());
		
		//se encienden los cohetes == activa los hilos de sus propulsores
		r1.rocketON();
		r2.rocketON();
		
		rocketsController(r1, r2);
		
		r1.rocketOFF();
		r2.rocketOFF();
		
		System.out.println(Thread.currentThread());
		try {
			Thread.currentThread().join(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Cohetes apagados!");
		
			
	}
	
	public static void setPropTarget(ArrayList<Propeller> propellers) {
		for(Propeller prop : propellers) {
			String target = JOptionPane.showInputDialog("Potencia objetivo del propulsor " + prop.getPropId());
			int targetPower = Integer.parseInt(target);
			prop.setTargetPower(targetPower);
		}
	}
	
	public static void rocketsController(Rocket r1, Rocket r2) {
		boolean end = false;
		ArrayList<String> buttonList = new ArrayList<String>();
		for (int i=0; i<r1.getNumPropellers(); i++) {
			buttonList.add(r1.getPropellers().get(i).getPropId() + " + ");
			buttonList.add(r1.getPropellers().get(i).getPropId() + " - ");
		}
		for(int j=0; j<r2.getNumPropellers(); j++) {
			buttonList.add(r2.getPropellers().get(j).getPropId() + " + ");
			buttonList.add(r2.getPropellers().get(j).getPropId() + " - ");
		}
		buttonList.add("STOP");
		String[] buttons = new String[buttonList.size()];
		for(int i = 0; i<buttons.length; i++) {
			buttons[i] = buttonList.get(i);
		}

		while(!end) {
			int opt = JOptionPane.showOptionDialog(null, null, "CONTROL PANEL",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons,
					buttons[0]);
			switch (opt) {
				case 0:
					action(r1, 0, "UP");
					break;
				case 1:
					action(r1, 0, "DOWN");
					break;
				case 2:
					action(r1, 1, "UP");
					break;
				case 3:
					action(r1, 1, "DOWN");
					break;
				case 4:
					action(r1, 2, "UP");
					break;
				case 5:
					action(r1, 2, "DOWN");
					break;
				case 6:
					action(r2, 0, "UP");
					break;
				case 7:
					action(r2, 0, "DOWN");
					break;
				case 8:
					action(r2, 1, "UP");
					break;
				case 9:
					action(r2, 1, "DOWN");
					break;
				case 10:
					action(r2, 2, "UP");
					break;
				case 11:
					action(r2, 2, "DOWN");
					break;
				case 12:
					action(r2, 3, "UP");
					break;
				case 13:
					action(r2, 3, "DOWN");
					break;
				case 14:
					action(r2, 4, "UP");
					break;
				case 15:
					action(r2, 4, "DOWN");
					break;
				case 16:
					action(r2, 5, "UP");
					break;
				case 17:
					action(r2, 5, "DOWN");
					break;
				case 18:
					end = true;
					break;
			}
		}
	}
	public static void action(Rocket r, int prop, String action) {
		if (action.equals("UP"))
			r.powerUp(prop);
		else
			r.powerDown(prop);
	}
	

}
