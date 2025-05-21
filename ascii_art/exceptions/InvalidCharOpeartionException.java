package ascii_art.exceptions;

import java.io.IOException;

public abstract class InvalidCharOpeartionException extends IOException {
    public InvalidCharOpeartionException() {
        super();
    }

    public InvalidCharOpeartionException(String message) {
        super(message);
    }
}
