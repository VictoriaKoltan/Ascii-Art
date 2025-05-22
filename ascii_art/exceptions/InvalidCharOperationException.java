package ascii_art.exceptions;

import java.io.IOException;

/**
 * Abstract base class for exceptions related to character operations.
 * This class serves as a parent for specific character operation exceptions.
 */
public abstract class InvalidCharOperationException extends IOException {
    /**
     * Constructs an InvalidCharOpeartionException with no message.
     */
    public InvalidCharOperationException() {
        super();
    }

    /**
     * Constructs an InvalidCharOpeartionException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidCharOperationException(String message) {
        super(message);
    }
}
