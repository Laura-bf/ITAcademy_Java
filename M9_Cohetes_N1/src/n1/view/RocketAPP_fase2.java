package n1.view;

import n1.model.Propeller;
import n1.model.Rocket;

public class RocketAPP_fase2 {

	public static void main(String[] args) throws Exception {
		Rocket r1 = new Rocket();
		r1.setCode("32wessds");
		r1.addPropellers(new Propeller(10));
		r1.addPropellers(new Propeller(30));
		r1.addPropellers(new Propeller(80));

		String maxPowersR1 = "";
		for (Propeller p : r1.getPropellers()) {
			maxPowersR1 += p.getMaxPower() + " ";
		}

		Rocket r2 = new Rocket();
		r2.setCode("ldsfja32");
		r2.addPropellers(new Propeller(30));
		r2.addPropellers(new Propeller(40));
		r2.addPropellers(new Propeller(50));
		r2.addPropellers(new Propeller(50));
		r2.addPropellers(new Propeller(30));
		r2.addPropellers(new Propeller(10));

		String maxPowersR2 = "";
		for (Propeller p : r2.getPropellers()) {
			maxPowersR2 += p.getMaxPower() + " ";
		}

		System.out.println(r1);
		System.out.println("Potencia máxima propulsores: [" + maxPowersR1.toString() + "]");
		System.out.println(r2);
		System.out.println("Potencia máxima propulsores: [" + maxPowersR2.toString() + "]");

	}

}
