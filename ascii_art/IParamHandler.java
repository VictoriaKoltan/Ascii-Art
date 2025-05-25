package ascii_art;

import ascii_art.exceptions.ParamException;
import image.Image;

/**
 * Interface for handling parameters and commands in the ASCII Art application.
 * Classes implementing this interface can process command arguments and provide
 * current parameter values.
 */
public interface IParamHandler {
    /**
     * Handles a command with its arguments and a reference image.
     * 
     * @param args command arguments
     * @param img  reference image
     * @throws ParamException if the parameters are invalid
     */
    void handleCommand(String[] args, Image img) throws ParamException;


}
