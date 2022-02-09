package eu.man.challenge.shared.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
