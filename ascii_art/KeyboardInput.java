package ascii_art;

import java.util.Scanner;

/**
 * Singleton class for handling keyboard input in the ASCII art application.
 * Provides a central point for reading user input from the console.
 */
class KeyboardInput {
    /**
     * Singleton instance of KeyboardInput
     */
    private static KeyboardInput keyboardInputObject = null;

    /**
     * Scanner used to read input from System.in
     */
    private Scanner scanner;

    /**
     * Private constructor for singleton pattern.
     * Initializes the scanner for System.in.
     */
    private KeyboardInput() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the singleton instance of KeyboardInput.
     * Creates a new instance if one doesn't exist yet.
     *
     * @return the singleton instance
     */
    public static KeyboardInput getObject() {
        if (KeyboardInput.keyboardInputObject == null) {
            KeyboardInput.keyboardInputObject = new KeyboardInput();
        }
        return KeyboardInput.keyboardInputObject;
    }

    /**
     * Reads a line of input from the console and trims whitespace.
     *
     * @return the trimmed line read from console
     */
    public static String readLine() {
        return KeyboardInput.getObject().scanner.nextLine().trim();
    }
}