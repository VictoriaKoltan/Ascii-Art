package ascii_art;

import ascii_art.exceptions.InvalidOutputException;
import image.Image;

class OutputHandler implements IParamHandler {
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
    }

}
