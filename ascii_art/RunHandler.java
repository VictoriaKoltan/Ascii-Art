package ascii_art;

import image.Image;

/**
 * Handler for run commands in the ASCII art application.
 * Currently a placeholder implementation.
 */
public class RunHandler implements IParamHandler {

    /**
     * Creates a new RunHandler.
     */
    public RunHandler() {

    }

    /**
     * Gets the command name.
     * 
     * @return "run" as the command name
     */
    @Override
    public String get() {
        return "run";
    }

    /**
     * Handles run commands.
     * Currently not implemented.
     * 
     * @param args command arguments
     * @param img  reference image
     */
    @Override
    public void handleCommand(String[] args, Image img) {

    }

}
