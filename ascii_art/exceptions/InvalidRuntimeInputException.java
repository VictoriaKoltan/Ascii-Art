package ascii_art.exceptions;

/**
 * Exception thrown when runtime input is invalid.
 * This is a runtime exception that indicates an unexpected error during
 * execution.
 */
public class InvalidRuntimeInputException extends RuntimeException {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * input command.
     */
    public static final String MESSAGE = ">>> ";

    /**
     * Constructs an InvalidRuntimeInputException with a default message.
     */
    public InvalidRuntimeInputException() {
        super(MESSAGE);
    }
}
