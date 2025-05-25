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

    /**
     * The original image to be converted to ASCII art.
     * This image is processed by the algorithm to generate corresponding ASCII
     * characters.
     */
    private final Image image;

    /**
     * The resolution for the ASCII art conversion.
     * This determines the level of detail in the output ASCII art.
     * Higher resolution results in more detailed ASCII representation of the image.
     */
    private final int resolution;
    /**
     * Matcher that matches brightness of subimages to ASCII characters.
     */
    private final SubImgCharMatcher matcher;

    /**
     * Constructs an algorithm run with the required parameters.
     *
     * @param image          the image to convert
     * @param matcher        matcher that will match brightness-pixel
     * @param resolution     block size resolution (e.g., 16, 32)
     * @param roundingMethod method for rounding brightness to character
     */
    public AsciiArtAlgorithm(Image image, SubImgCharMatcher matcher, int resolution,
            RoundingMethod roundingMethod) {
        this.image = image;
        this.resolution = resolution;
        this.matcher = matcher;
        matcher.setRounding(roundingMethod);
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
        int blockSize = paddedImage.getWidth() / resolution;
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
}
