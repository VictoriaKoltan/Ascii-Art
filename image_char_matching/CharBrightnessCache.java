package image_char_matching;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import ascii_art.RoundingMethod;

/**
 * A helper class responsible for caching character brightness values (raw and
 * normalized),
 * and matching brightness values to characters efficiently.
 */
class CharBrightnessCache {
    private final Map<Character, Double> rawBrightnessMap; // Stores brightness before normalization
    private final Map<Character, Double> normalizedBrightnessMap; // Stores brightness after normalization
    private final TreeSet<Character> sortedCharset; // Keeps characters sorted by ASCII for tie-breaking

    // Default rounding method
    private RoundingMethod roundingMethod = RoundingMethod.ABS;

    /**
     * constructor
     */
    public CharBrightnessCache() {
        rawBrightnessMap = new HashMap<>();
        normalizedBrightnessMap = new HashMap<>();
        sortedCharset = new TreeSet<>();
    }

    /**
     * Sets the rounding method to use when comparing brightness values.
     * 
     * @param method the rounding method to use
     */
    public void setRoundingMethod(RoundingMethod method) {
        if (method != null) {
            this.roundingMethod = method;
        }
    }

    /**
     * Gets the current rounding method being used.
     * 
     * @return the current rounding method
     */
    public RoundingMethod getRoundingMethod() {
        return roundingMethod;
    }

    /**
     * Adds a character to the cache, computes its brightness, and updates
     * normalization.
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
     * Removes a character from the cache and updates normalization.
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
     * Returns the character whose normalized brightness is closest to the given
     * value.
     * 
     * @param brightness normalized brightness in range [0, 1]
     * @return closest matching character
     */
    public char getClosestChar(double brightness) {
        char bestChar = '?';
        double minDiff = Double.MAX_VALUE;

        // Iterate over all cached characters
        for (char c : sortedCharset) {
            double rawDiff = normalizedBrightnessMap.get(c) - brightness;

            // Apply appropriate rounding method
            double diff;
            switch (roundingMethod) {
                case UP:
                    diff = (rawDiff > 0) ? rawDiff : 0; // Consider only positive differences
                    break;
                case DOWN:
                    diff = (rawDiff < 0) ? -rawDiff : 0; // Consider only negative differences
                    break;
                case ABS:
                default:
                    diff = Math.abs(rawDiff);
                    break;
            }

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
     * Performs linear normalization for all raw brightness values into [0, 1]
     * range.
     * Updates the normalizedBrightnessMap accordingly.
     */
    private void normalizeBrightness() {
        // Avoid normalization if only one or zero values exist
        if (rawBrightnessMap.size() < 2)
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
}
