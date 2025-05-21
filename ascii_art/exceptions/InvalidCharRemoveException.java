package ascii_art.exceptions;

public class InvalidCharRemoveException extends InvalidCharOpeartionException {
    public InvalidCharRemoveException() {
        super("Did not remove due to incorrect format.");
    }
}
