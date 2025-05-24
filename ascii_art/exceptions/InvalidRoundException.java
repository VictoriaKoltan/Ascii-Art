package ascii_art.exceptions;

/**
 * Exception thrown when a rounding method specification is invalid.
 * This exception is thrown when trying to set a rounding method that is not
 * supported.
 */
public class InvalidRoundException extends ParamException {

    public static final String INCORRECT_FORMAT = "Did not change rounding method due to incorrect format.";

    /**
     * Constructs an InvalidRoundException with a message about rounding method.
     */
    public InvalidRoundException() {
        super(INCORRECT_FORMAT);
    }
}
