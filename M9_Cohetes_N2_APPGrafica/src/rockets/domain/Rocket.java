package rockets.domain;

import java.util.ArrayList;

public class Rocket {
	
	private String code;
	private double speed;
	private double targetSpeed;
	private ArrayList<Propeller> propellers;
	
	public Rocket(String code) {
		this.code = code;
		this.speed = 0;
		this.propellers = new ArrayList<Propeller>();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double calculateCurrentSpeed() {
		return speed + (100*Math.sqrt(this.getTotalCurrentPower()));
	}
	
	public double getTargetSpeed() {
		return targetSpeed;
	}
	
	public void setTargetSpeed(double targetSpeed) {
			this.targetSpeed = targetSpeed;
	}
	
	public int getTotalMaxPower() {
		ArrayList<Propeller> propellers = this.getPropellers();
		int totalMaxPower = 0;
		for(Propeller prop : propellers) {
			totalMaxPower += prop.getMaxPower();
		}
		return totalMaxPower;
	}
	
	public int getTotalCurrentPower() {
		ArrayList<Propeller> propellers = this.getPropellers();
		int totalCurrentPower = 0;
		for(Propeller prop : propellers) {
			totalCurrentPower += prop.getCurrentPower();
		}
		return totalCurrentPower;
	}
	
	public void addPropeller(Propeller prop) {
		this.propellers.add(prop);
	}
	
	public int getNumPropellers() {
		int numProps = 0;
		if(propellers != null)
			numProps = propellers.size();
		return numProps;
	}
	
	public ArrayList<Propeller> getPropellers(){
		return propellers;
	}

	public void rocketON() {
		if (this.propellers != null) {
			for (Propeller p : this.propellers) {
				new Thread(p).start();
			}
		}
	}

	public void rocketOFF() {
		if (this.propellers != null) {
			for (Propeller p : this.propellers) {
				p.setStoppedProp(true);
			}
		}
	}
	
	public void powerUp() {
		for(Propeller prop : this.getPropellers()) {
			prop.powerUp();
		}
	}
	
	public void powerDown() {
		for(Propeller prop : this.getPropellers()) {
			prop.powerDown();
		}
	}

	@Override
	public String toString() {
		return "Rocket id=" + code + " Velocidad=" + speed +"Objetivo="+ targetSpeed +"\nPropulsores:\n" + getPropellers().toString() ;
	}
}
