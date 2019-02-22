package exceptions;

public class InvalidTurnoverException extends RuntimeException {

    public InvalidTurnoverException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTurnoverException(String message) {
        super(message);
    }
}
