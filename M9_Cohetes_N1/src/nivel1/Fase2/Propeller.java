package nivel1.Fase2;

public class Propeller {
	
	private int maxPower;

	public Propeller(int maxPower) {
		this.maxPower = maxPower;
	}

	public int getMaxPower() {
		return maxPower;
	}
	
	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	@Override
	public String toString() {
		return "Propeller [maxPower=" + maxPower + "]";
	}

}

