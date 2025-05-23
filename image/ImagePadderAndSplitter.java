package image;

import java.awt.Color;

import ascii_art.exceptions.InvalidImageException;
//TODO  למה בחבילה הזאת ולמה מחלקה נפרדת  README להוסיף

/**
 * Handles padding an image to power-of-two dimensions and splitting it into
 * square blocks.
 */
public class ImagePadderAndSplitter {

    private static final int WHITE = 255;

    /**
     * Pads the given image with white pixels to make both width and height powers
     * of 2.
     * Padding is symmetric on all sides.
     *
     * @param original the original image
     * @return new Image object with padded size
     * @throws InvalidImageException
     */
    public static Image padToPowerOfTwo(Image original) throws InvalidImageException {
        int originalWidth = original.getWidth();
        int originalHeight = original.getHeight();

        int newWidth = nextPowerOfTwo(originalWidth);
        int newHeight = nextPowerOfTwo(originalHeight);

        if (newWidth == originalWidth && newHeight == originalHeight)
            return original;

        // Calculate symmetric offsets for centering the original image
        int xOffset = (newWidth - originalWidth) / 2;
        int yOffset = (newHeight - originalHeight) / 2;

        Color[][] paddedPixels = new Color[newHeight][newWidth];

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                paddedPixels[y][x] = new Color(WHITE, WHITE, WHITE);
            }
        }

        for (int y = 0; y < originalHeight; y++) {
            for (int x = 0; x < originalWidth; x++) {
                paddedPixels[y + yOffset][x + xOffset] = original.getPixel(y, x);
            }
        }
        // TODO: ADD EXCEPTION HANDLING
        return new Image(paddedPixels, newWidth, newHeight);
    }

    /**
     * Splits the image into non-overlapping square subimages of given blockSize.
     * Assumes image dimensions are divisible by blockSize.
     *
     * @param image     the input image to split
     * @param blockSize the size (width and height) of each square block
     * @return 2D array of subimages
     */
    public static Image[][] splitToSubImages(Image image, int blockSize) {
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        int rows = imgHeight / blockSize;
        int cols = imgWidth / blockSize;

        Image[][] subImages = new Image[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color[][] pixels = new Color[blockSize][blockSize];

                // Copy blockSize × blockSize pixels into each subimage
                for (int y = 0; y < blockSize; y++) {
                    for (int x = 0; x < blockSize; x++) {
                        pixels[y][x] = image.getPixel(row * blockSize + y, col * blockSize + x);
                    }
                }

                subImages[row][col] = new Image(pixels, blockSize, blockSize);
            }
        }
        return subImages;
    }

    /**
     * Helper function to compute the next power of two greater than or equal to n.
     * 
     * @param n input integer
     * @return smallest power of 2 >= n
     */
    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2; // Multiply until reaching a power >= n
        }
        return power;
    }
}
