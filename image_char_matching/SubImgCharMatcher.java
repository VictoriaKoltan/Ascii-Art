package image_char_matching;

import ascii_art.RoundingMethod;

/**
 * Manages ASCII characters and their brightness values,
 * and provides functionality to match image brightness to characters.
 */
public class SubImgCharMatcher {
    private final CharBrightnessCache cache;

    /**
     * Constructor that initializes the matcher with a given character set.
     * 
     * @param charset an array of ASCII characters
     */
    public SubImgCharMatcher(char[] charset, RoundingMethod roundingMethod) {
        cache = new CharBrightnessCache();
        cache.setRoundingMethod(roundingMethod);
        for (char c : charset) {
            cache.addChar(c);
        }
    }

    /**
     * Returns the character whose normalized brightness is closest to the input.
     * If multiple characters have the same brightness difference, returns the one
     * with lowest ASCII value.
     * 
     * @param brightness normalized image brightness in range [0, 1]
     * @return the best matching ASCII character
     */
    public char getCharByImageBrightness(double brightness) {
        return cache.getClosestChar(brightness);
    }

    /**
     * Adds a new character to the character set (if not already included).
     * 
     * @param c character to add
     */
    public void addChar(char c) {
        cache.addChar(c);
    }

    /**
     * Removes a character from the character set (if it exists).
     * 
     * @param c character to remove
     */
    public void removeChar(char c) {
        cache.removeChar(c);
    }
}
