package ascii_art.exceptions;

import java.io.IOException;

/**
 * Exception thrown when an image path is invalid or the image cannot be loaded.
 * This exception extends IOException to indicate that it relates to file
 * input/output problems.
 */
public class InvalidImageException extends IOException {
    /**
     * Constructs an InvalidImageException with a message providing the problematic
     * path.
     *
     * @param message the invalid image path or error details
     */
    public InvalidImageException(String message) {
        super("Invalid image path argument: " + message);
    }

    /**
     * Constructs an InvalidImageException with a default message.
     * Used when specific path details are not available.
     */
    public InvalidImageException() {
        super("Invalid image path argument");
    }
}
