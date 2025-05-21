package ascii_art;

import java.util.Map;

import ascii_art.exceptions.InvalidCommandException;
import ascii_art.exceptions.InvalidImageException;
import ascii_art.exceptions.ParamException;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

public class Shell {

    private final Map<String, IParamHandler> handlers;
    private final CharSet charSet;
    private final ResHandler resHandler;
    private final RoundHandler roundHandler;
    private final OutputHandler outputHandler;

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

    private boolean handleInstruction(String instruction, Image img) throws InvalidImageException {
        if (instruction.equals("asciiArt")) {
            AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(img, charSet.getChars(), resHandler.getInt());
            char[][] result = algorithm.run();
            outputHandler.out(result);
            return true;
        } else if (instruction.equals("exit")) {
            return false;
        }
        return handleSetting(instruction, img);
    }

    public void run(String imageName) throws InvalidImageException {

        boolean toContinue = true;
        String imagePath = imageName;
        Image image = new Image(imagePath);
        while (toContinue) {
            System.out.print(">>>");
            String instruction = KeyboardInput.readLine();
            toContinue = handleInstruction(instruction, image);
        }

    }

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
