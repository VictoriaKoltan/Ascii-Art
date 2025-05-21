package ascii_art.exceptions;

/**
 * Exception thrown when an output format specification is invalid.
 * This exception is thrown when trying to set an output format that is not
 * supported.
 */
public class InvalidOutputException extends ParamException {

    /**
     * Constructs an InvalidOutputException with a message about output format.
     */
    public InvalidOutputException() {
        super("output format");
    }
}
