package ascii_art;

import ascii_art.exceptions.InvalidRoundException;
import ascii_art.exceptions.ParamException;
import image.Image;

class RoundHandler implements IParamHandler {
    enum RoundingMethod {
        UP("up"),
        DOWN("down"),
        ABS("abs");

        private final String value;

        RoundingMethod(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static RoundingMethod fromString(String str) throws ParamException {
            for (int i = 0; i < values().length; i++) {
                RoundingMethod method = values()[i];
                if (method.getValue().equals(str)) {
                    return method;
                }
            }
            throw new InvalidRoundException();
        }
    }

    private RoundingMethod roundingMethod = RoundingMethod.ABS;

    @Override
    public String get() {
        return roundingMethod.getValue();
    }

    @Override
    public void handleCommand(String[] args, Image img) throws ParamException {
        if (args.length < 2) {
            throw new InvalidRoundException();
        }

        String arg = args[1];
        roundingMethod = RoundingMethod.fromString(arg);
    }
}
