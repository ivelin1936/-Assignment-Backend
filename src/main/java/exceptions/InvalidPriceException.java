package exceptions;

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}
