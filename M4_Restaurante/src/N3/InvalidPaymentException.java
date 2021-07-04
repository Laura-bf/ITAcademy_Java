package N3;

public class InvalidPaymentException extends Exception {
	public InvalidPaymentException() {}
	public InvalidPaymentException(String errorMessage) {
		super(errorMessage);
	}
}
