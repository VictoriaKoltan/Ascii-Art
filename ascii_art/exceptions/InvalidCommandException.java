package ascii_art.exceptions;

/**
 * Exception thrown when a command is invalid or unrecognized.
 * This exception is thrown when the shell encounters a command that it cannot
 * process.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an InvalidCommandException with a default message.
     */
    public InvalidCommandException() {
        super("Did not execute due to incorrect command.");
    }
}
