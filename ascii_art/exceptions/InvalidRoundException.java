package ascii_art.exceptions;

/**
 * Exception thrown when a rounding method specification is invalid.
 * This exception is thrown when trying to set a rounding method that is not
 * supported.
 */
public class InvalidRoundException extends ParamException {

    /**
     * Constructs an InvalidRoundException with a message about rounding method.
     */
    public InvalidRoundException() {
        super("rounding method");
    }
}
