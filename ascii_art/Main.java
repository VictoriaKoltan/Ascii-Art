package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import image.Image;
import java.io.IOException;

/**
 * Main entry point for the ASCII Art application.
 * Demonstrates basic usage of the ASCII Art algorithm with a sample image.
 */
public class Main {
    /**
     * Main method that runs the ASCII Art conversion process.
     * Loads an image, sets a resolution and charset, runs the algorithm,
     * and outputs the result to the console.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Load image from file
            Image image = new Image("board.jpeg");

            // Set resolution and charset
            int resolution = 2;
            char[] charset = { 'm', 'o' };

            // Run algorithm
            AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(image, charset, resolution);
            char[][] result = algorithm.run();

            // Output to console
            new ConsoleAsciiOutput().out(result);
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }
    }
}
