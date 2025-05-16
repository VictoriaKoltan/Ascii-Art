package image_char_matching;

import java.util.*;

/**
 * A class responsible for matching image brightness to the closest ASCII character
 * from a given character set.
 */
public class SubImgCharMatcher {

    /**
     * Constructs a matcher with an initial character set.
     * Computes and stores the normalized brightness values.
     *
     * @param charset an array of ASCII characters to be used for matching
     */
    public SubImgCharMatcher(char[] charset) {
        // Initialize sets and compute brightness values
    }

    /**
     * Given a brightness value between 0 and 1, returns the character
     * with the closest brightness from the current character set.
     *
     * @param brightness the grayscale brightness value (between 0 and 1)
     * @return the ASCII character closest in brightness
     */
    public char getCharByImageBrightness(double brightness) {
        // Match to closest brightness (use abs or rounding strategy)
        return '?'; // placeholder
    }

    /**
     * Adds a new character to the charset and updates brightness maps.
     * If the character is already in the set, it is ignored.
     *
     * @param c the character to add
     */
    public void addChar(char c) {
        // Add character and recompute brightness normalization
    }

    /**
     * Removes a character from the charset and updates brightness maps.
     * If the character is not in the set, nothing happens.
     *
     * @param c the character to remove
     */
    public void removeChar(char c) {
        // Remove character and recompute normalization
    }

}