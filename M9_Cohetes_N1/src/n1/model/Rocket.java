package n1.model;

import java.util.ArrayList;
import java.util.List;

public class Rocket {
	private String code;
	private List<Propeller> propellers = new ArrayList<Propeller>();

	public Rocket() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws Exception {
		if (code.length() != 8)
			throw new Exception("WRONG CODE!! SHOULD BE 8 CHAR LONG");
		this.code = code.toUpperCase();
	}

	public List<Propeller> getPropellers() {
		return propellers;
	}

	public void addPropellers(Propeller prop) {
		propellers.add(prop);
	}

	@Override
	public String toString() {
		return "Rocket id=" + code + ": Tiene " + propellers.size() + " propulsores";
	}

}
