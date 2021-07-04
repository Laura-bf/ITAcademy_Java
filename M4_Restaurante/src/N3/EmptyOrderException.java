package N3;

public class EmptyOrderException extends Exception {
	public EmptyOrderException() {}
	public EmptyOrderException(String errorMessage) {
		super(errorMessage);		
	}
	@Override
	public String toString() {
		return "EmptyOrderException []";
	}

}
