package ascii_art.exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Did not execute due to incorrect command.");
    }

}
