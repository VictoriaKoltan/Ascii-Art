package ascii_art.exceptions;

/**
 * Exception thrown when a command is invalid or unrecognized.
 * This exception is thrown when the shell encounters a command that it cannot
 * process.
 */
public class InvalidCommandException extends Exception {
    /**
     * A constant message to be used when an exception is thrown due to an incorrect
     * format
     */
    public static final String MESSAGE = "Did not execute due to incorrect command.";

    /**
     * Constructs an InvalidCommandException with a default message.
     */
    public InvalidCommandException() {
        super(MESSAGE);
    }
}
