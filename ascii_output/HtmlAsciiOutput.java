package ascii_output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Output a 2D array of chars to an HTML file viewable in a web browser.
 * 
 * @author Dan Nirel
 */
public class HtmlAsciiOutput implements AsciiOutput {
    /**
     * Base line spacing factor for HTML output
     */
    private static final double BASE_LINE_SPACING = 0.8;

    /**
     * Base font size for HTML output
     */
    private static final double BASE_FONT_SIZE = 150.0;

    /**
     * Font name to use in the HTML output
     */
    private final String fontName;

    /**
     * Filename for the HTML output
     */
    private final String filename;

    /**
     * Creates a new HTML ASCII art output handler.
     *
     * @param filename the name of the file to write to
     * @param fontName the font to use in the HTML output
     */
    public HtmlAsciiOutput(String filename, String fontName) {
        this.fontName = fontName;
        this.filename = filename;
    }

    /**
     * Outputs the ASCII art to an HTML file.
     * Handles special characters like &lt;, &gt;, and &amp; by converting them to
     * HTML entities.
     * The font size is automatically adjusted based on the width of the ASCII art.
     *
     * @param chars the 2D array of characters representing the ASCII art
     */
    @Override
    public void out(char[][] chars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.format(
                    "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body style=\"" +
                            "\tCOLOR:#000000;" +
                            "\tTEXT-ALIGN:center;" +
                            "\tFONT-SIZE:1px;\">\n" +
                            "<p style=\"" +
                            "\twhite-space:pre;" +
                            "\tFONT-FAMILY:%s;" +
                            "\tFONT-SIZE:%frem;" +
                            "\tLETTER-SPACING:0.15em;" +
                            "\tLINE-HEIGHT:%fem;\">\n",
                    fontName, BASE_FONT_SIZE / chars[0].length, BASE_LINE_SPACING));

            for (int y = 0; y < chars.length; y++) {
                for (int x = 0; x < chars[y].length; x++) {
                    String htmlRep;
                    switch (chars[y][x]) {
                        case '<':
                            htmlRep = "&lt;";
                            break;
                        case '>':
                            htmlRep = "&gt;";
                            break;
                        case '&':
                            htmlRep = "&amp;";
                            break;
                        default:
                            htmlRep = String.valueOf(chars[y][x]);
                    }
                    writer.write(htmlRep);
                }
                writer.newLine();
            }
            writer.write(
                    "</p>\n" +
                            "</body>\n" +
                            "</html>\n");
        } catch (IOException e) {
            Logger.getGlobal().severe(String.format("Failed to write to \"%s\"", filename));
        }
    }
}
