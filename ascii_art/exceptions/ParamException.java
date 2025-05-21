package ascii_art.exceptions;

/**
 * Base exception class for parameter-related errors in the ASCII art
 * application.
 * This exception is thrown when a command parameter is invalid or incorrectly
 * formatted.
 */
public class ParamException extends Exception {

    /**
     * Constructs a ParamException with a message specifying which parameter type
     * was incorrect.
     *
     * @param paramType the type of parameter that had an incorrect format
     */
    public ParamException(String paramType) {
        super(String.format("Did not change %s due to incorrect format.", paramType));
    }

    /**
     * Constructs a ParamException with a default message.
     * Used when the specific parameter type is not known or relevant.
     */
    public ParamException() {
        super(">>>");
    }
}
