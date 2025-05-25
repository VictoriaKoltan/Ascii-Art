package ascii_art.exceptions;

/**
 * Exception thrown when a resolution command is invalid.
 * This exception is thrown when trying to set a resolution that is out of
 * bounds or invalid.
 */
public class InvalidResCommandException extends ParamException {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * resolution command.
     */
    public static final String MSG = "Did not change resolution due to incorrect format.";
    /**
     * Constructs an InvalidResCommandException with a message about rounding
     * method.
     */
    public InvalidResCommandException() {
        super(MSG);
    }
    /**
     * Constructs an InvalidResCommandException with a message about rounding
     * method.
     * @param@
     */
    public InvalidResCommandException(String command) {
        super(command);
    }
}
