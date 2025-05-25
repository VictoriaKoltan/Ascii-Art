package ascii_art.exceptions;

/**
 * Exception thrown when a rounding method specification is invalid.
 * This exception is thrown when trying to set a rounding method that is not
 * supported.
 */
public class InvalidRoundException extends ParamException {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * round command.
     */
    public static final String MSG = "Did not change rounding method due to incorrect format.";

    /**
     * Constructs an InvalidRoundException with a message about rounding method.
     */
    public InvalidRoundException() {
        super(MSG);
    }
}
