package ascii_art;

import ascii_art.exceptions.InvalidResCommandException;
import image.Image;

/**
 * Handler for resolution commands in the ASCII art application.
 * Manages the number of characters per row in the output.
 */
class ResHandler implements IParamHandler {

    /**
     * Characters per row as a string
     */
    private String charsPerRow;

    /**
     * initializing with a default resolution of 2 characters
     * per row.
     */
    private static final int DEFAULT_CHARS_PER_ROW = 2;

    /**
     * Number of arguments expected for resolution commands.
     */
    private static final int ARG_COUNT = 2;
    /**
     * Multiplier for resolution commands.
     * Used to double the resolution when increasing.
     */
    private static final int MULTIPLIER = 2;

    private static final String EXCEEDING_BOUNDARIES_ERR = "Did not change resolution due to exceeding boundaries.";

    /**
     * Creates a new ResHandler with the specified number of characters per row.
     * 
     */
    public ResHandler() {
        this.charsPerRow = String.valueOf(DEFAULT_CHARS_PER_ROW);
    }

    /**
     * Handles the resolution up command, doubling the number of characters per row.
     *
     * @param img reference image for size constraints
     * @throws InvalidResCommandException if the new resolution exceeds the image
     *                                    width
     */
    private void handleResUpCommand(Image img) throws InvalidResCommandException {
        int resInt = Integer.parseInt(charsPerRow);
        resInt *= MULTIPLIER;
        if (resInt > img.getWidth()) {
            throw new InvalidResCommandException(EXCEEDING_BOUNDARIES_ERR);
        }
        charsPerRow = String.valueOf(resInt);
    }

    /**
     * Handles the resolution down command, halving the number of characters per
     * row.
     *
     * @param img reference image for size constraints
     * @throws InvalidResCommandException if the new resolution is below the minimum
     *                                    allowed
     */
    private void handleResDownCommand(Image img) throws InvalidResCommandException {
        int resInt = Integer.parseInt(charsPerRow);
        int minValue = Math.max(img.getWidth() / img.getHeight(), 1);
        resInt /= MULTIPLIER;
        if (resInt < minValue) {
            throw new InvalidResCommandException(EXCEEDING_BOUNDARIES_ERR);
        }
        charsPerRow = String.valueOf(resInt);
    }

    /**
     * Gets the current resolution as a string.
     * 
     * @return string representation of the resolution
     */

    public int getInt() {
        return Integer.parseInt(charsPerRow);
    }

    /**
     * Handles resolution commands.
     * 
     * @param args command arguments
     * @param img  reference image for size constraints
     * @throws InvalidResCommandException if the command is invalid
     */
    @Override
    public void handleCommand(String[] args, Image img) throws InvalidResCommandException {

        if (args.length < ARG_COUNT) {
            System.out.println(charsPerRow);
            return;
        }

        String arg = args[1];
        if (arg.equals("up")) {
            handleResUpCommand(img);
            System.out.println("Resolution set to " + charsPerRow);

        } else if (arg.equals("down")) {
            handleResDownCommand(img);
            System.out.println("Resolution set to " + charsPerRow);
        } else {
            throw new InvalidResCommandException();
        }
    }
}
