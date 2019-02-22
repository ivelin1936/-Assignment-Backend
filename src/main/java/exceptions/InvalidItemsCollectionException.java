package exceptions;

public class InvalidItemsCollectionException extends RuntimeException {

    public InvalidItemsCollectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidItemsCollectionException(String message) {
        super(message);
    }
}
