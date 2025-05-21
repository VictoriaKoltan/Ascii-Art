package ascii_art.exceptions;

import java.io.IOException;

/**
 * Abstract base class for exceptions related to character operations.
 * This class serves as a parent for specific character operation exceptions.
 * Note: There is a typo in the class name (Opeartion instead of Operation),
 * but it's maintained for compatibility.
 */
public abstract class InvalidCharOpeartionException extends IOException {
    /**
     * Constructs an InvalidCharOpeartionException with no message.
     */
    public InvalidCharOpeartionException() {
        super();
    }

    /**
     * Constructs an InvalidCharOpeartionException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidCharOpeartionException(String message) {
        super(message);
    }
}
