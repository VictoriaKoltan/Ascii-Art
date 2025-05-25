package ascii_art.exceptions;

/**
 * Exception thrown when runtime input is invalid.
 * This is a runtime exception that indicates an unexpected error during
 * execution.
 */
public class InvalidRuntimeInputException extends RuntimeException {

    public static final String MESSAGE = ">>> ";

    /**
     * Constructs an InvalidRuntimeInputException with a default message.
     */
    public InvalidRuntimeInputException() {
        super(MESSAGE);
    }
}
