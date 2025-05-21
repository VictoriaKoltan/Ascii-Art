package image;

import javax.imageio.ImageIO;

import ascii_art.exceptions.InvalidImageException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A package-private class of the package image.
 * Represents an image that can be loaded from a file or created from a pixel
 * array.
 * 
 * @author Dan Nirel
 */
public class Image {

    /**
     * 2D array of Colors representing the pixels of the image
     */
    private final Color[][] pixelArray;

    /**
     * Width of the image in pixels
     */
    private final int width;

    /**
     * Height of the image in pixels
     */
    private final int height;

    /**
     * Constructs an Image object by loading an image from a file.
     *
     * @param filename path to the image file to be loaded
     * @throws InvalidImageException if the image file cannot be loaded
     */
    public Image(String filename) throws InvalidImageException {
        BufferedImage im;
        try {
            im = ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new InvalidImageException(filename);
        }
        width = im.getWidth();
        height = im.getHeight();

        pixelArray = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixelArray[i][j] = new Color(im.getRGB(j, i));
            }
        }

    }

    /**
     * Constructs an Image object from a 2D array of Color objects.
     *
     * @param pixelArray 2D array of Colors representing the pixels of the image
     * @param width      width of the image in pixels
     * @param height     height of the image in pixels
     */
    public Image(Color[][] pixelArray, int width, int height) {
        this.pixelArray = pixelArray;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the image.
     *
     * @return width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the image.
     *
     * @return height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the Color of a specific pixel in the image.
     *
     * @param x row index of the pixel
     * @param y column index of the pixel
     * @return Color object representing the pixel's color
     */
    public Color getPixel(int x, int y) {
        return pixelArray[x][y];
    }

    /**
     * Saves the current image to a JPEG file.
     *
     * @param fileName name of the file to save (without extension)
     */
    public void saveImage(String fileName) {
        // Initialize BufferedImage, assuming Color[][] is already properly
        // populated.
        BufferedImage bufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
                BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int x = 0; x < pixelArray.length; x++) {
            for (int y = 0; y < pixelArray[x].length; y++) {
                bufferedImage.setRGB(y, x, pixelArray[x][y].getRGB());
            }
        }
        File outputfile = new File(fileName + ".jpeg");
        try {
            ImageIO.write(bufferedImage, "jpeg", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
