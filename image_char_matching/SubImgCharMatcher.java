package image_char_matching;

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
    public SubImgCharMatcher(char[] charset) {
        cache = new CharBrightnessCache();
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
        return cache.getCharByImageBrightness(brightness);
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

    //TODO להסביר למה הוספנו מתודות פומביות למחלקה
    /**
     * Returns the character from the charset whose brightness is the smallest value
     * that is greater than or equal to the given brightness. If no such character exists,
     * returns the brightest character as a fallback.
     *
     * @param brightness the normalized brightness value to round up from (in range [0, 1])
     * @return the character with the closest brightness ≥ given value, or the brightest character
     */
    public char getCharByBrightnessUp(double brightness) {
        return cache.getCharByBrightnessUp(brightness);
    }

    /**
     * Returns the character from the charset whose brightness is the largest value
     * that is less than or equal to the given brightness. If no such character exists,
     * returns the darkest character as a fallback.
     *
     * @param brightness the normalized brightness value to round down from (in range [0, 1])
     * @return the character with the closest brightness ≤ given value, or the darkest character
     */
    public char getCharByBrightnessDown(double brightness) {
        return cache.getCharByBrightnessDown(brightness);
    }

}

