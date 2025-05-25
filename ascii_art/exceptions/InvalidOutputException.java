package ascii_art.exceptions;

/**
 * Exception thrown when an output format specification is invalid.
 * This exception is thrown when trying to set an output format that is not
 * supported.
 */
public class InvalidOutputException extends ParamException {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * format
     */
    public static final String INCORRECT_FORMAT = "Did not change output format due to incorrect format.";

    /**
     * Constructs an InvalidOutputException with a message about output format.
     */
    public InvalidOutputException() {
        super(INCORRECT_FORMAT);
    }
}
