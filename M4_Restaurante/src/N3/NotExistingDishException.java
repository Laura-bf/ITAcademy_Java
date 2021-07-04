package N3;

public class NotExistingDishException extends Exception {
	public NotExistingDishException() {}
	public NotExistingDishException(String errorMessage) {
		super(errorMessage);
	}
	
	@Override
	public String toString() {
		return "NotExistingDishException []";
	}
	

}
