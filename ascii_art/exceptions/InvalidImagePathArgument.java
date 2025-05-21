package ascii_art.exceptions;

import java.io.IOException;

public class InvalidImagePathArgument extends IOException {
    public InvalidImagePathArgument(String message) {
        super("Invalid image path argument: " + message);
    }

}
