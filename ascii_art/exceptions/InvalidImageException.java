package ascii_art.exceptions;

import java.io.IOException;

/**
 * Exception thrown when an image path is invalid or the image cannot be loaded.
 * This exception extends IOException to indicate that it relates to file
 * input/output problems.
 */
public class InvalidImageException extends IOException {
    public static final String INVALID_IMAGE_PATH_ARGUMENT = "Invalid image path argument: ";
    public static final String MESSAGE = "Invalid image path argument";

    /**
     * Constructs an InvalidImageException with a message providing the problematic
     * path.
     *
     * @param message the invalid image path or error details
     */
    public InvalidImageException(String message) {
        super(INVALID_IMAGE_PATH_ARGUMENT + message);
    }

    /**
     * Constructs an InvalidImageException with a default message.
     * Used when specific path details are not available.
     */
    public InvalidImageException() {
        super(MESSAGE);
    }
}
