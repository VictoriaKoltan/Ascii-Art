package image_char_matching;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import ascii_art.RoundingMethod;

/**
 * Manages ASCII characters and their brightness values,
 * and provides functionality to match image brightness to characters.
 */
public class SubImgCharMatcher {
    // Stores brightness before normalization
    private final Map<Character, Double> rawBrightnessMap;
    // Stores brightness after normalization
    private final Map<Character, Double> normalizedBrightnessMap;
    // Keeps characters sorted by ASCII for tie-breaking
    private final TreeSet<Character> sortedCharset;

    // Minimum size for normalization
    private static final int MIN_CHAR_MAP_SIZE = 2;
    private RoundingMethod roundingMethod;

    /**
     * Constructor that initializes the matcher with a given character set.
     * 
     * @param charset an array of ASCII characters
     */
    public SubImgCharMatcher(char[] charset) {
        rawBrightnessMap = new HashMap<>();
        normalizedBrightnessMap = new HashMap<>();
        sortedCharset = new TreeSet<>();
        for (char c : charset) {
            addChar(c);
        }
    }

    /**
     * Sets the rounding method used for character brightness matching.
     * 
     * @param roundingMethod the rounding method to use (UP, DOWN, or ABS)
     */
    public void setRounding(RoundingMethod roundingMethod) {
        this.roundingMethod = roundingMethod;
    }

    /**
     * Gets the set of all characters available in the matcher.
     * 
     * @return a sorted set of all characters
     */
    public TreeSet<Character> getChars() {
        return sortedCharset;
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
     * Computes raw brightness of a character using CharConverter.
     * Brightness is the ratio of black pixels (true) to total pixels (always 256).
     *
     * @param c character to evaluate
     * @return raw brightness (black pixels / total)
     */
    private double computeCharBrightness(char c) {
        boolean[][] matrix = CharConverter.convertToBoolArray(c);
        int black = 0;
        int total = matrix.length * matrix[0].length; // 16×16 = 256

        // Count black pixels (true values)
        for (boolean[] row : matrix) {
            for (boolean pixel : row) {
                if (pixel) { // true = black pixel
                    black++;
                }
            }
        }

        return (double) black / total; // Normalize to [0,1]
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
                return getCharByBrightnessUp(brightness);
            case DOWN:
                return getCharByBrightnessDown(brightness);
            case ABS:
            default:
                return getCharByBrightnessAbs(brightness);
        }
    }

    /**
     * Performs linear normalization for all raw brightness values into [0, 1]
     * range.
     * Updates the normalizedBrightnessMap accordingly.
     */
    private void normalizeBrightness() {
        // Avoid normalization if only one or zero values exist
        if (rawBrightnessMap.size() < MIN_CHAR_MAP_SIZE)
            return;

        // Find min and max brightness among all characters
        double min = Collections.min(rawBrightnessMap.values());
        double max = Collections.max(rawBrightnessMap.values());

        normalizedBrightnessMap.clear();

        for (char c : sortedCharset) {
            double raw = rawBrightnessMap.get(c);

            // Linear normalization formula:
            // normalized = (raw - min) / (max - min)
            double normalized = (raw - min) / (max - min);

            normalizedBrightnessMap.put(c, normalized);
        }
    }

    /**
     * Finds and returns the character in the charset with the highest normalized
     * brightness value.
     * This represents the brightest ASCII character in terms of visual lightness.
     *
     * @return the character with the maximum normalized brightness,
     *         or '?' if the charset is empty
     */
    private char getBrightestChar() {
        char best = '?';
        double max = -1;
        for (char c : sortedCharset) {
            double b = normalizedBrightnessMap.get(c);
            if (b > max) {
                max = b;
                best = c;
            }
        }
        return best;
    }

    /**
     * Adds a new character to the character set (if not already included).
     * 
     * @param c character to add
     */
    public void addChar(char c) {
        // If already added, do nothing
        if (sortedCharset.contains(c))
            return;

        // Compute and cache raw brightness
        double brightness = computeCharBrightness(c);
        rawBrightnessMap.put(c, brightness);

        // Add to sorted set for ordered iteration
        sortedCharset.add(c);

        // Update normalized brightness values
        normalizeBrightness();
    }

    /**
     * Removes a character from the character set (if it exists).
     * 
     * @param c character to remove
     */
    public void removeChar(char c) {
        // If not present, do nothing
        if (!sortedCharset.contains(c))
            return;

        // Remove all traces of the character
        rawBrightnessMap.remove(c);
        normalizedBrightnessMap.remove(c);
        sortedCharset.remove(c);

        // Re-normalize brightness values
        normalizeBrightness();
    }


    /**
     * Returns the character with brightness = input, closest from abs.
     *
     * @param brightness normalized brightness
     * @return best character match
     */

    private char getCharByBrightnessAbs(double brightness) {
        char bestChar = '?';
        double minDiff = Double.MAX_VALUE;

        // Iterate over all cached characters
        for (char c : sortedCharset) {
            double diff = Math.abs(normalizedBrightnessMap.get(c) - brightness);

            // Pick the character with the smallest difference,
            // break ties by choosing the one with lower ASCII value
            if (diff < minDiff || (diff == minDiff && c < bestChar)) {
                minDiff = diff;
                bestChar = c;
            }
        }
        return bestChar;
    }



    /**
     * Returns the character with brightness ≥ input, closest from above.
     * If none found, returns the brightest character.
     *
     * @param brightness normalized brightness
     * @return best character match
     */

    public char getCharByBrightnessUp(double brightness) {
        char fallback = '?';
        double minAbove = Double.MAX_VALUE;

        for (char c : sortedCharset) {
            double b = normalizedBrightnessMap.get(c);
            if (b >= brightness && b < minAbove) {
                minAbove = b;
                fallback = c;
            }
        }
        return fallback != '?' ? fallback : getBrightestChar();
    }

    /**
     * Returns the character with brightness ≤ input, closest from below.
     * If none found, returns the darkest character.
     *
     * @param brightness normalized brightness
     * @return best character match
     */

    public char getCharByBrightnessDown(double brightness) {
        char fallback = '?';
        double maxBelow = -1;

        for (char c : sortedCharset) {
            double b = normalizedBrightnessMap.get(c);
            if (b <= brightness && b > maxBelow) {
                maxBelow = b;
                fallback = c;
            }
        }
        return fallback != '?' ? fallback : getDarkestChar();
    }

    /**
     * Finds and returns the character in the charset with the lowest normalized
     * brightness value.
     * This represents the darkest ASCII character in terms of visual density.
     *
     * @return the character with the minimum normalized brightness,
     *         or '?' if the charset is empty
     */
    private char getDarkestChar() {
        char best = '?';
        double min = Double.MAX_VALUE;
        for (char c : sortedCharset) {
            double b = normalizedBrightnessMap.get(c);
            if (b < min) {
                min = b;
                best = c;
            }
        }
        return best;
    }

}
