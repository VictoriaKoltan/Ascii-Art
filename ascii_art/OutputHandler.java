package ascii_art;

import ascii_art.exceptions.InvalidOutputException;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

/**
 * Handler for ASCII art output operations.
 * Manages the output format (HTML or console) and related settings.
 */
class OutputHandler implements IParamHandler {

    /**
     * The ASCII output implementation to use
     */
    private AsciiOutput asciiOutput;

    /**
     * Font to use for HTML output
     */
    private String font;

    /**
     * Filename to use for HTML output
     */
    private String filename;

    /**
     * Creates a new OutputHandler with the specified font and filename.
     * 
     * @param font     font to use for HTML output
     * @param filename filename to use for HTML output
     */

    /**
     * Current output format, defaults to console
     */
    private OutputFormat format = OutputFormat.CONSOLE;

    public OutputHandler(String font, String filename) {
        this.font = font;
        this.filename = filename;
        this.asciiOutput = new ConsoleAsciiOutput();

    }

    /**
     * Enumeration for supported output formats.
     */
    enum OutputFormat {
        HTML("html"),
        CONSOLE("console"),;

        /**
         * String representation of the format
         */
        private final String value;

        /**
         * Creates a new OutputFormat with the specified value.
         * 
         * @param value string representation of the format
         */
        OutputFormat(String value) {
            this.value = value;
        }

        /**
         * Gets the string representation of the format.
         * 
         * @return string representation
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string to an OutputFormat.
         * 
         * @param str string to convert
         * @return corresponding OutputFormat
         * @throws InvalidOutputException if the string doesn't match any format
         */
        public static OutputFormat fromString(String str) throws InvalidOutputException {
            for (OutputFormat format : values()) {
                if (format.getValue().equals(str)) {
                    return format;
                }
            }
            throw new InvalidOutputException();
        }
    }

    /**
     * Outputs the ASCII art using the current output format.
     * 
     * @param chars 2D character array representing the ASCII art
     */
    public void out(char[][] chars) {
        asciiOutput.out(chars);
    }

    /**
     * Sets the font for HTML output.
     * 
     * @param font font name to use
     */
    public void setFont(String font) {
        this.font = font;
    }

    /**
     * Sets the filename for HTML output.
     * 
     * @param filename filename to use
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Gets the string representation of the current output format.
     * 
     * @return string representation of the format
     */
    @Override
    public String get() {
        return format.getValue();
    }

    /**
     * Handles output commands and changes the output format.
     * 
     * @param args command arguments
     * @param img  reference image (not used)
     * @throws InvalidOutputException if the arguments are invalid
     */
    @Override
    public void handleCommand(String[] args, Image img) throws InvalidOutputException {
        if (args.length < 2) {
            throw new InvalidOutputException();
        }

        String arg = args[1];
        format = OutputFormat.fromString(arg);
        switch (format) {
            case HTML:
                asciiOutput = new HtmlAsciiOutput(filename, font);
                break;
            case CONSOLE:
                asciiOutput = new ConsoleAsciiOutput();
                break;
        }
    }

}
