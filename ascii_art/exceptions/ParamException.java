package ascii_art.exceptions;

public class ParamException extends Exception {

    public ParamException(String paramType) {
        super(String.format("Did not change %s due to incorrect format.", paramType));
    }

    public ParamException() {
        super(">>>");
    }
}
