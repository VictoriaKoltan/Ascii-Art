package ascii_art;

import ascii_art.exceptions.InvalidOutputException;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

class OutputHandler implements IParamHandler {

    private AsciiOutput asciiOutput;
    private String font;
    private String filename;

    public OutputHandler(String font, String filename) {
        this.font = font;
        this.filename = filename;
    }

    enum OutputFormat {
        HTML("html"),
        CONSOLE("console"),;

        private final String value;

        OutputFormat(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static OutputFormat fromString(String str) throws InvalidOutputException {
            for (OutputFormat format : values()) {
                if (format.getValue().equals(str)) {
                    return format;
                }
            }
            throw new InvalidOutputException();
        }
    }

    private OutputFormat format = OutputFormat.CONSOLE;

    public void out(char[][] chars) {
        asciiOutput.out(chars);
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String get() {
        return format.getValue();
    }

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
            case CONSOLE:
                asciiOutput = new ConsoleAsciiOutput();
        }
    }

}
