package ascii_art;

import ascii_art.exceptions.InvalidRoundException;
import ascii_art.exceptions.ParamException;
import image.Image;

/**
 * Handler for rounding method commands in the ASCII art application.
 * Manages how brightness values are rounded when matching characters.
 */
class RoundHandler implements IParamHandler {

    /**
     * Current rounding method, defaults to ABS (absolute difference)
     */
    private RoundingMethod roundingMethod = RoundingMethod.ABS;

    /**
     * Gets the string representation of the current rounding method.
     * 
     * @return string representation of the rounding method
     */
    @Override
    public String get() {
        return roundingMethod.getValue();
    }

    /**
     * Returns the current rounding method set to this handler.
     *
     * @return the current rounding method
     */
    public RoundingMethod getRoundingMethod() {
        return roundingMethod;
    }

    /**
     * Handles rounding method commands.
     * 
     * @param args command arguments
     * @param img  reference image (not used)
     * @throws ParamException if the command is invalid
     */
    @Override
    public void handleCommand(String[] args, Image img) throws ParamException {
        if (args.length < 2) {
            throw new InvalidRoundException();
        }

        String arg = args[1];
        roundingMethod = RoundingMethod.fromString(arg);
    }
}
