package ascii_art;

import ascii_art.exceptions.InvalidOutputException;
import ascii_art.exceptions.ParamException;
import image.Image;

public interface IParamHandler {
    void handleCommand(String[] args, Image img) throws ParamException;

    String get();
}
