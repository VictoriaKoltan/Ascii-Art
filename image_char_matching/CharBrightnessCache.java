package image_char_matching;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
//TODOזאת אומרת ממש את המתודות בקלס , API להסביר שהקלס עוטף את ה
/**
 * A helper class responsible for caching character brightness values (raw and normalized),
 * and matching brightness values to characters efficiently.
 */
class CharBrightnessCache {
    private final Map<Character, Double> rawBrightnessMap;           // Stores brightness before normalization
    private final Map<Character, Double> normalizedBrightnessMap;    // Stores brightness after normalization
    private final TreeSet<Character> sortedCharset;                  // Keeps characters sorted by ASCII for tie-breaking

    /**
     * constructor
     * */
    public CharBrightnessCache() {
        rawBrightnessMap = new HashMap<>();
        normalizedBrightnessMap = new HashMap<>();
        sortedCharset = new TreeSet<>();
    }

    /**
     * Adds a character to the cache, computes its brightness, and updates normalization.
     * @param c character to add
     */
    public void addChar(char c) {
        // If already added, do nothing
        if (sortedCharset.contains(c)) return;

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
     * @param c character to remove
     */
    public void removeChar(char c) {
        // If not present, do nothing
        if (!sortedCharset.contains(c)) return;

        // Remove all traces of the character
        rawBrightnessMap.remove(c);
        normalizedBrightnessMap.remove(c);
        sortedCharset.remove(c);

        // Re-normalize brightness values
        normalizeBrightness();
    }

    /**
     * Returns the character whose normalized brightness is closest to the given value.
     * @param brightness normalized brightness in range [0, 1]
     * @return closest matching character
     */
    public char getClosestChar(double brightness) {
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
     * Computes raw brightness of a character using CharConverter.
     * Brightness is the ratio of black pixels (true) to total pixels (always 256).
     * @param c character to evaluate
     * @return raw brightness (black pixels / total)
     */
    private double computeCharBrightness(char c) {
        boolean[][] matrix = CharConverter.convertToBoolArray(c);
        int black = 0;
        int total = matrix.length * matrix[0].length;  //  16×16 = 256

        // Count black pixels (true values)
        for (boolean[] row : matrix) {
            for (boolean pixel : row) {
                if (pixel) { // true = black pixel
                    black++;
                }
            }
        }

        return (double) black / total;  // Normalize to [0,1]
    }

    /**
     * Performs linear normalization for all raw brightness values into [0, 1] range.
     * Updates the normalizedBrightnessMap accordingly.
     */
    private void normalizeBrightness() {
        // Avoid normalization if only one or zero values exist
        if (rawBrightnessMap.size() < 2) return;

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
