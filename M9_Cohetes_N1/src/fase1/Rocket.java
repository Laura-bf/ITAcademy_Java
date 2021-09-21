package fase1;

public class Rocket {
	private String code;
	private int numProps;
	
	public Rocket(String code, int numProps) throws Exception {
		if(code.length()!=8)
			throw new Exception("WRONG CODE!! SHOULD BE 8 CHAR LONG");
		if(numProps==0)
			throw new Exception("AT LEAST ONE PROP NEEDED!!");
		this.code = code.toUpperCase();
		this.numProps = numProps;	
	}

	@Override
	public String toString() {
		return "Rocket id=" + code + ": Tiene " + numProps + " propulsores";
	}
	
	
	
	
}
