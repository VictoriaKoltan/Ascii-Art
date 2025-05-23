package ascii_art.exceptions;

/**
 * Exception thrown when a resolution command is invalid.
 * This exception is thrown when trying to set a resolution that is out of
 * bounds or invalid.
 */
public class InvalidResCommandException extends ParamException {

    /**
     * Constructs an InvalidResCommandException with a message about rounding
     * method.
     */
    public InvalidResCommandException() {
        super("rounding method");
    }
}
