package nivel1.Fase3;

import java.util.ArrayList;

public class Rocket {
	
	private String code;
	private ArrayList<Propeller> propellers;
	
	public Rocket(String code) {
		this.code = code;
		this.propellers = new ArrayList<Propeller>();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
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

	public void powerUp(int propId) {
		if (propId <= this.propellers.size() - 1) {
			this.propellers.get(propId).powerUp();
		} else {
			System.out.println("ERROR - no existe el propulsor!");
		}
	}

	public void powerDown(int propId) {
		if (propId <= this.propellers.size() - 1) {
			this.propellers.get(propId).powerDown();
		} else {
			System.out.println("ERROR - no existe el propulsor!");
		}
	}

	@Override
	public String toString() {
		return "Rocket id=" + code + "\nPropulsores:\n" + getPropellers().toString() ;
	}
}
