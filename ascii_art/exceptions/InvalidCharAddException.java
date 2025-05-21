package ascii_art.exceptions;

/**
 * Exception thrown when attempting to add an invalid character to the character
 * set.
 * This exception is thrown when the character specification is in an incorrect
 * format.
 */
public class InvalidCharAddException extends InvalidCharOpeartionException {
    /**
     * Constructs an InvalidCharAddException with a default message.
     */
    public InvalidCharAddException() {
        super("Did not add due to incorrect format.");
    }
}