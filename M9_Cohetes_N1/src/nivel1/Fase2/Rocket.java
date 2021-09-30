package nivel1.Fase2;

public class Rocket {

	private String code;
	private Propeller[] propellers;

	public Rocket(String code, int[] maxPowers) {
		this.code = code;
		this.propellers = new Propeller[maxPowers.length];
		for (int i = 0; i < maxPowers.length; i++) {
			Propeller prop = new Propeller(maxPowers[i]);
			propellers[i] = prop;
		}
	}

	public String getCode() {
		return code;
	}

	public Propeller[] getPropellers() {
		return propellers;
	}

	public String getMaxPowers() {
		String maxPowers = "";
		for (Propeller p : propellers) {
			maxPowers += p.getMaxPower() + " ";
		}
		return maxPowers;
	}

	@Override
	public String toString() {
		return "Rocket id=" + code + "\nPotencia máxima propulsores: " + getMaxPowers();
	}

}