package ascii_art.exceptions;

import java.io.IOException;

/**
 * Exception thrown when a resolution command is invalid.
 * This exception is thrown when trying to set a resolution that is out of
 * bounds or invalid.
 */
public class InvalidResCommandException extends ParamException {

    public static final String MSG = "Did not change resolution due to exceeding boundaries.";

    /**
     * Constructs an InvalidResCommandException with a message about rounding
     * method.
     */
    public InvalidResCommandException() {
        super(MSG);
    }
}
