package image;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for computing brightness of images and caching results.
 * Includes support for Memento pattern to save/restore cache state.
 */
public class BrightnessCalculator {

    // Cache to store brightness values of subimages, using hashed pixel data as key
    private static final Map<String, Double> BRIGHTNESS_CACHE = new HashMap<>();

    /**
     * Grayscale weight for the red channel.
     */
    private static final double RED_WEIGHT = 0.2126;

    /**
     * Grayscale weight for the green channel.
     */
    private static final double GREEN_WEIGHT = 0.7152;

    /**
     * Grayscale weight for the blue channel.
     */
    private static final double BLUE_WEIGHT = 0.0722;

    /**
     * max RGB value for normalization.
     */
    private static final double MAX_RGB_VALUE = 255.0;

    /**
     * Computes the brightness of a given image (sub-image).
     * Uses grayscale conversion and normalization.
     * Caches result to avoid recomputation.
     *
     * @param image the image to compute brightness for
     * @return brightness in range [0, 1]
     */
    public static double computeBrightness(Image image) {
        String key = generateImageKey(image);
        if (BRIGHTNESS_CACHE.containsKey(key)) {
            return BRIGHTNESS_CACHE.get(key);
        }

        double totalGrey = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = image.getPixel(y, x);

                // Human-perception-based grayscale weights
                double grey = color.getRed() * RED_WEIGHT
                        + color.getGreen() * GREEN_WEIGHT
                        + color.getBlue() * BLUE_WEIGHT;

                // Normalize pixel brightness to [0,1]
                double normalizedGrey = grey / MAX_RGB_VALUE;

                totalGrey += normalizedGrey;
            }
        }

        // Average brightness across all pixels
        double brightness = totalGrey / (width * height);

        // Store computed brightness in cache
        BRIGHTNESS_CACHE.put(key, brightness);
        return brightness;
    }

    /**
     * Memento of the current brightness cache.
     * 
     * @return a BrightnessSnapshot with current cached data
     */
    public static BrightnessSnapshot createSnapshot() {
        // Copy the current cache to preserve its state
        return new BrightnessSnapshot(new HashMap<>(BRIGHTNESS_CACHE));
    }

    /**
     * Restores the brightness cache from a given snapshot.
     * 
     * @param snapshot the snapshot to restore
     */
    public static void restoreSnapshot(BrightnessSnapshot snapshot) {
        BRIGHTNESS_CACHE.clear();
        BRIGHTNESS_CACHE.putAll(snapshot.getCache());
    }

    /**
     * Generates a single per-image string key representing the pixel content of the
     * image.
     * Concatenates RGB values of all pixels row-by-row.
     *
     * @param image the image to hash
     * @return string key
     */
    private static String generateImageKey(Image image) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color c = image.getPixel(y, x);
                sb.append(c.getRGB()).append(",");
            }
        }
        return sb.toString();
    }

    /**
     * Memento class for storing a snapshot of the brightness cache.
     * Used to restore previous state to avoid redundant recalculations.
     */
    public static class BrightnessSnapshot {
        /**
         * A cache that stores computed brightness values for characters.
         * The key is a String char and the value is its fitting brightness
         */
        private final Map<String, Double> cache;

        /**
         * Constructor for BrightnessSnapshot.
         * Creates a new BrightnessSnapshot with the provided cache mapping.
         *
         * @param cache Map
         */
        public BrightnessSnapshot(Map<String, Double> cache) {
            this.cache = cache;
        }

        /**
         * Returns the current state of the cache.
         * 
         * @return a Map containing character strings as keys and their corresponding
         *         brightness values as Double
         */
        public Map<String, Double> getCache() {
            return cache;
        }
    }
}
