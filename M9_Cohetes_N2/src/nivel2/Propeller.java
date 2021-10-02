package nivel2;

public class Propeller implements Runnable {  
	
	private String propId;
	private int maxPower;
	private int currentPower;
	private int targetPower;
	private boolean stoppedProp;
	
	public Propeller(String propId, int maxPower) {
		this.propId = propId;
		this.maxPower = maxPower;
		currentPower = 0;
		targetPower = 0;
		stoppedProp = false;
	}
	
	public String getPropId() {
		return propId;
	}
	
	public void setPropId(String propId) {
		this.propId = propId;
	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		if (maxPower > 0)
			this.maxPower = maxPower;
		else
			this.maxPower = 0;
	}
	
	public int getCurrentPower() {
		return currentPower;
	}
	
	public void setCurrentPower(int currentPower) {
		this.currentPower = currentPower;
	}
	
	public int getTargetPower() {
		return targetPower;
	}
	
	public void setTargetPower(int targetPower) {
		if(targetPower>maxPower) {
			this.targetPower = maxPower;
			System.err.println("El objetivo no puede superar la potencia máxima");
		}else if(targetPower<0)
			this.targetPower = 0;
		else
			this.targetPower = targetPower;
	}
	
	public boolean isStoppedProp() {
		return stoppedProp;
	}
	
	public void setStoppedProp(boolean stopProp) {
		this.stoppedProp = stopProp;
	}
	
	//para aumentar la potencia
	public int powerUp() {
		if(currentPower < targetPower)
			currentPower++;
		return currentPower;
	}
	
	//para bajar la potencia
	public int powerDown() {
		if(currentPower > 0)
			currentPower--;
		return currentPower;
	}
	
	@Override
	public String toString() {
		return propId + ": Actual=" + currentPower + " Objetivo=" + targetPower + " Max.="+ maxPower;
	}

	@Override
	public void run() {
		while (!this.isStoppedProp()) {
			if (this.getCurrentPower() != this.getTargetPower()) 
				System.out.println(this.toString());
			else
				System.out.println(this.getPropId() + " Potencia conseguida: Actual=" + this.getCurrentPower() + 
						" Objetivo=" + this.getTargetPower() + " Máximo=" + this.getMaxPower());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Propulsor " + this.getPropId() + " inactivo!");
	}

}


