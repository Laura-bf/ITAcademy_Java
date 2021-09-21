package n1.model;

public class Propeller {
	private int maxPower;

	public Propeller(int maxPower) {
		this.maxPower = maxPower;
	}

	public int getMaxPower() {
		return maxPower;
	}

	@Override
	public String toString() {
		return "Propeller [maxPower=" + maxPower + "]";
	}

}
