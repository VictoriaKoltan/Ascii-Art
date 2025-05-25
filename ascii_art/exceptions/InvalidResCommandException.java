package ascii_art.exceptions;

/**
 * Exception thrown when a resolution command is invalid.
 * This exception is thrown when trying to set a resolution that is out of
 * bounds or invalid.
 */
/**
 * Exception thrown when an invalid resolution command is encountered.
 * This exception extends ParamException to handle specific errors related to
 * resolution commands.
 */
public class InvalidResCommandException extends ParamException {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * resolution command.
     */
    public static final String MSG = "Did not change resolution due to incorrect format.";

    /**
     * Constructs an InvalidResCommandException with the default message.
     */
    public InvalidResCommandException() {
        super(MSG);
    }

    /**
     * Constructs an InvalidResCommandException with a specific command string.
     * 
     * @param command The resolution command that caused the exception
     */
    public InvalidResCommandException(String command) {
        super(command);
    }
}
