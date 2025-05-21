package image_char_matching;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Inspired by, and partly copied from
 * https://github.com/korhner/asciimg/blob/95c7764a6abe0e893fae56b3b6b580e09e1de209/src/main/java/io/korhner/asciimg/image/AsciiImgCache.java
 * described in the blog:
 * https://dzone.com/articles/ascii-art-generator-java
 * Adaptations made by Dan Nirel and again by Rachel Behar.
 * The class converts characters to a binary "image" (2D array of booleans).
 */
public class CharConverter {
    /**
     * Horizontal offset factor for character positioning
     */
    private static final double X_OFFSET_FACTOR = 0.2;

    /**
     * Vertical offset factor for character positioning
     */
    private static final double Y_OFFSET_FACTOR = 0.75;

    /**
     * Font name used for rendering characters
     */
    private static final String FONT_NAME = "Courier New";

    /**
     * Default resolution in pixels for character rendering
     */
    public static final int DEFAULT_PIXEL_RESOLUTION = 16;

    /**
     * Renders a given character, according to how it looks in the font specified in
     * the
     * constructor, to a square black&white image (2D array of booleans),
     * whose dimension in pixels is specified.
     *
     * @param c character to convert
     * @return 2D boolean array representing the character image
     */
    public static boolean[][] convertToBoolArray(char c) {
        BufferedImage img = getBufferedImage(c, FONT_NAME, DEFAULT_PIXEL_RESOLUTION);
        boolean[][] matrix = new boolean[DEFAULT_PIXEL_RESOLUTION][DEFAULT_PIXEL_RESOLUTION];
        for (int y = 0; y < DEFAULT_PIXEL_RESOLUTION; y++) {
            for (int x = 0; x < DEFAULT_PIXEL_RESOLUTION; x++) {
                matrix[y][x] = img.getRGB(x, y) == 0; // is the color white
            }
        }
        return matrix;
    }

    /**
     * Creates a BufferedImage with the given character rendered on it.
     *
     * @param c            character to render
     * @param fontName     name of the font to use
     * @param pixelsPerRow size of the image in pixels (both width and height)
     * @return BufferedImage containing the rendered character
     */
    private static BufferedImage getBufferedImage(char c, String fontName, int pixelsPerRow) {
        String charStr = Character.toString(c);
        Font font = new Font(fontName, Font.PLAIN, pixelsPerRow);
        BufferedImage img = new BufferedImage(pixelsPerRow, pixelsPerRow, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setFont(font);
        int xOffset = (int) Math.round(pixelsPerRow * X_OFFSET_FACTOR);
        int yOffset = (int) Math.round(pixelsPerRow * Y_OFFSET_FACTOR);
        g.drawString(charStr, xOffset, yOffset);
        return img;
    }

}
