package ascii_art.exceptions;

/**
 * Exception thrown when attempting to remove an invalid character from the
 * character set.
 * This exception is thrown when the character specification is in an incorrect
 * format.
 */
public class InvalidCharRemoveException extends InvalidCharOperationException {
    /**
     * Constructs an InvalidCharRemoveException with a default message.
     */
    public InvalidCharRemoveException() {
        super("Did not remove due to incorrect format.");
    }
}
