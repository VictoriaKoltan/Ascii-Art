package ascii_art;

import image.Image;
import image.ImagePadderAndSplitter;
import ascii_art.exceptions.InvalidImageException;
import image.BrightnessCalculator;
import image_char_matching.SubImgCharMatcher;

/**
 * A single execution of the ASCII Art Algorithm.
 * Responsible for converting an input image to a 2D ASCII character array.
 */
public class AsciiArtAlgorithm {

    private final Image image;
    private final int resolution;
    private final SubImgCharMatcher matcher;
    private final RoundingMode roundingMode;


    /**
     * Enum to define rounding
     */
    public enum RoundingMode {
        ABS, UP, DOWN
    }

    /**
     * Constructs an algorithm run with the required parameters.
     *
     * @param image      the image to convert
     * @param charset    the character set to use for brightness matching
     * @param resolution block size resolution (e.g., 16, 32)
     */
    public AsciiArtAlgorithm(Image image, char[] charset, int resolution, RoundingMode roundingMode) {
        this.image = image;
        this.resolution = resolution;
        this.roundingMode = roundingMode;
        this.matcher = new SubImgCharMatcher(charset);
    }

    /**
     * Runs the ASCII Art algorithm.
     *
     * Steps:
     * 1. Pads the image to dimensions that are powers of two.
     * 2. Splits the image into square subimages of given resolution.
     * 3. Computes brightness for each subimage.
     * 4. Matches brightness to the closest ASCII character from the charset.
     *
     * @return a 2D character array representing the ASCII art.
     * @throws InvalidImageException
     */
    public char[][] run() throws InvalidImageException {
        Image paddedImage = ImagePadderAndSplitter.padToPowerOfTwo(image);
        int blockSize = paddedImage.getWidth()/resolution;
        Image[][] subImages = ImagePadderAndSplitter.splitToSubImages(paddedImage, blockSize);
        int rows = subImages.length;
        int cols = subImages[0].length;

        char[][] asciiArt = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double brightness = BrightnessCalculator.computeBrightness(subImages[row][col]);
                asciiArt[row][col] = matcher.getCharByImageBrightness(brightness);
            }
        }

        return asciiArt;
}

    /**
     * Matches a brightness value to the appropriate ASCII character,
     * based on the current rounding mode (ABS, UP, or DOWN).
     *
     * @param brightness the normalized brightness value in the range [0, 1]
     * @return the ASCII character whose brightness best matches the input value
     */
    private char matchByBrightness(double brightness) {
        switch (roundingMode) {
            case UP:
                return matcher.getCharByBrightnessUp(brightness);
            case DOWN:
                return matcher.getCharByBrightnessDown(brightness);
            case ABS:
            default:
                return matcher.getCharByImageBrightness(brightness);
        }
    }

}