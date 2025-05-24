package ascii_art;

import java.io.IOException;
import java.util.Map;

import ascii_art.exceptions.InvalidCommandException;
import ascii_art.exceptions.InvalidImageException;
import ascii_art.exceptions.ParamException;
import image.Image;

/**
 * Command-line shell for the ASCII Art application.
 * Handles user commands and manages the ASCII Art generation process.
 */
public class Shell {

    /**
     * Map of command names to their handlers
     */
    private final Map<String, IParamHandler> handlers;

    /**
     * Handler for character set commands
     */
    private final CharSet charSet;

    /**
     * Handler for resolution commands
     */
    private final ResHandler resHandler;

    /**
     * Handler for rounding commands
     */
    private final RoundHandler roundHandler;

    /**
     * Handler for output commands
     */
    private final OutputHandler outputHandler;

    /**
     * Initializes the shell with default handlers and settings.
     */
    public Shell() {
        charSet = new CharSet();
        resHandler = new ResHandler(2);
        roundHandler = new RoundHandler();
        outputHandler = new OutputHandler("Courier New", "out.html");
        handlers = Map.of(
                "add", charSet,
                "remove", charSet,
                "chars", charSet,
                "res", resHandler,
                "round", roundHandler,
                "output", outputHandler);
    }

    /**
     * Handles setting commands by delegating to the appropriate handler.
     *
     * @param instruction the command string
     * @param img         the image being processed
     * @return true to continue processing commands, false to exit
     */
    private boolean handleSetting(String instruction, Image img) {
        try {
            String[] args = instruction.split(" ");
            String op = args[0];
            IParamHandler handler = handlers.get(op);
            if (handler == null) {
                throw new InvalidCommandException();
            }
            handler.handleCommand(args, img);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (ParamException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Handles all instructions including ASCII art generation and exit.
     *
     * @param instruction the command string
     * @param img         the image being processed
     * @return true to continue processing commands, false to exit
     * @throws InvalidImageException if there is an issue with the image
     */
    private boolean handleInstruction(String instruction, Image img) throws InvalidImageException {
        if (instruction.equals("asciiArt")) {
            AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(
                    img,
                    charSet.getChars(),
                    resHandler.getInt(),
                    roundHandler.getRoundingMethod()
                    );
            char[][] result = algorithm.run();
            outputHandler.out(result);
            return true;
        } else if (instruction.equals("exit")) {
            return false;
        }
        return handleSetting(instruction, img);
    }

    /**
     * Runs the interactive shell with the specified image.
     *
     * @param imageName path to the image file
     * @throws InvalidImageException if the image cannot be loaded
     */
    /**
     * Runs the interactive shell with the specified image.
     *
     * @param imageName path to the image file
     * @throws InvalidImageException if the image cannot be loaded
     */
    public void run(String imageName) throws InvalidImageException {
        try {

            boolean toContinue = true;
            String imagePath = imageName;
            Image image = new Image(imagePath);
            while (toContinue) {
                System.out.print(">>>");
                String instruction = KeyboardInput.readLine();
                toContinue = handleInstruction(instruction, image);
            }
        } catch (Exception ex) {
            throw new InvalidImageException();
        }
    }

    /**
     * Main entry point for the Shell application.
     *
     * @param args command-line arguments (expects an image path)
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new InvalidImageException("");
            }
            String imagePath = args[0];
            Shell shell = new Shell();
            shell.run(imagePath);
        } catch (InvalidImageException e) {
            System.out.println(e.getMessage());
        }
    }
}
