package ascii_art;

import ascii_art.exceptions.InvalidRoundException;

/**
     * Enumeration for supported rounding methods.
     */
/**
 * Enumeration representing different methods of rounding numeric values.
 * <p>
 * This enum provides three rounding strategies:
 * <ul>
 * <li>UP - rounds values away from zero</li>
 * <li>DOWN - rounds values toward zero</li>
 * <li>ABS - rounds based on absolute value</li>
 * </ul>
 */
public enum RoundingMethod {
    /**
     * Rounds values away from zero.
     */
    UP("up"),

    /**
     * Rounds values toward zero.
     */
    DOWN("down"),

    /**
     * Rounds based on absolute value.
     */
    ABS("abs");

    /**
     * String representation of the rounding method.
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
     * @throws InvalidRoundException
     */
    public static RoundingMethod fromString(String str) throws InvalidRoundException {
        for (int i = 0; i < values().length; i++) {
            RoundingMethod method = values()[i];
            if (method.getValue().equals(str)) {
                return method;
            }
        }
        throw new InvalidRoundException();
    }
}
