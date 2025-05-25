package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A package-private class of the package image.
 * Represents an image as a 2D array of Color objects.
 * 
 * @author Dan Nirel
 */
public class Image {

    /** 2D array storing the color of each pixel in the image */
    private final Color[][] pixelArray;
    /** Width of the image in pixels */
    private final int width;
    /** Height of the image in pixels */
    private final int height;

    /**
     * Constructs an Image object from a file.
     * 
     * @param filename the path to the image file
     * @throws IOException if the file cannot be read or is not a valid image
     */
    public Image(String filename) throws IOException {
        BufferedImage im = ImageIO.read(new File(filename));
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
     * @param pixelArray the 2D array of Color objects
     * @param width      the width of the image
     * @param height     the height of the image
     */
    public Image(Color[][] pixelArray, int width, int height) {
        this.pixelArray = pixelArray;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the image.
     * 
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the image.
     * 
     * @return the height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the color of a specific pixel in the image.
     * 
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     * @return the Color object representing the pixel's color
     */
    public Color getPixel(int x, int y) {
        return pixelArray[x][y];
    }

    /**
     * Saves the image to a file in JPEG format.
     * 
     * @param fileName the name of the file to save (without extension)
     */
    public void saveImage(String fileName) {
        // Initialize BufferedImage, assuming Color[][] is already properly populated.
        BufferedImage bufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
                BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int y = 0; y < pixelArray.length; y++) {
            for (int x = 0; x < pixelArray[y].length; x++) {
                bufferedImage.setRGB(y, x, pixelArray[y][x].getRGB());
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
