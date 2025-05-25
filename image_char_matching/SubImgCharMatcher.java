package image_char_matching;

import ascii_art.RoundingMethod;

import java.util.TreeSet;

/**
 * Manages ASCII characters and their brightness values,
 * and provides functionality to match image brightness to characters.
 */
public class SubImgCharMatcher {
    private final CharBrightnessCache cache;
    private RoundingMethod roundingMethod;
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
    public void setRounding(RoundingMethod roundingMethod){
        this.roundingMethod = roundingMethod;
    }

    public TreeSet<Character> getChars(){
        return cache.getChars();
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
        return matchByBrightness(brightness);
    }

    /**
     * Matches a brightness value to the appropriate ASCII character,
     * based on the current rounding mode (ABS, UP, or DOWN).
     *
     * @param brightness the normalized brightness value in the range [0, 1]
     * @return the ASCII character whose brightness best matches the input value
     */
    private char matchByBrightness(double brightness) {
        switch (roundingMethod) {
            case UP:
                return cache.getCharByBrightnessUp(brightness);
            case DOWN:
                return cache.getCharByBrightnessDown(brightness);
            case ABS:
            default:
                return cache.getCharByImageBrightness(brightness);
        }
    }

    /**
     * Adds a new character to the character set (if not already included).
     * @param c character to add
     */
    public void addChar(char c) {
        cache.addChar(c);
    }

    /**
     * Removes a character from the character set (if it exists).
     * @param c character to remove
     */
    public void removeChar(char c) {
        cache.removeChar(c);
    }



}
