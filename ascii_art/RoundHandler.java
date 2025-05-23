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
     * Enumeration for supported rounding methods.
     */
    enum RoundingMethod {
        UP("up"),
        DOWN("down"),
        ABS("abs");

        /**
         * String representation of the rounding method
         */
        private final String value;

        /**
         * Creates a new RoundingMethod with the specified value.
         * 
         * @param value string representation of the rounding method
         */
        RoundingMethod(String value) {
            this.value = value;
        }

        /**
         * Gets the string representation of the rounding method.
         * 
         * @return string representation
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string to a RoundingMethod.
         * 
         * @param str string to convert
         * @return corresponding RoundingMethod
         * @throws ParamException if the string doesn't match any rounding method
         */
        public static RoundingMethod fromString(String str) throws ParamException {
            for (int i = 0; i < values().length; i++) {
                RoundingMethod method = values()[i];
                if (method.getValue().equals(str)) {
                    return method;
                }
            }
            throw new InvalidRoundException();
        }
    }

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
