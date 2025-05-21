package ascii_art.exceptions;

public class InvalidCharAddException extends InvalidCharOpeartionException {
    public InvalidCharAddException() {
        super("Did not add due to incorrect format.");
    }

}