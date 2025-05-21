package ascii_art.exceptions;

import java.io.IOException;

public class InvalidImageException extends IOException {
    public InvalidImageException(String message) {
        super("Invalid image path argument: " + message);
    }

    public InvalidImageException() {
        super("Invalid image path argument");
    }
}
