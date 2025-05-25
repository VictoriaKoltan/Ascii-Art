package ascii_art.exceptions;

/**
 * Base exception class for parameter-related errors in the ASCII art
 * application.
 * This exception is thrown when a command parameter is invalid or incorrectly
 * formatted.
 */
public class ParamException extends Exception {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * param command.
     */
    public static final String MESSAGE = ">>> ";

    /**
     * Constructs a ParamException with a message specifying which parameter type
     * was incorrect.
     *
     * @param msg the Messagge of the exception
     */
    public ParamException(String msg) {
        super(msg);
    }

    /**
     * Constructs a ParamException with a default message.
     * Used when the specific parameter type is not known or relevant.
     */
    public ParamException() {
        super(MESSAGE);
    }
}
