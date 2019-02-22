package exceptions;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException(String message) {
        super(message);
    }
}
