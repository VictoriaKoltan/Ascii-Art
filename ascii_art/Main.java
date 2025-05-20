package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import image.Image;
import java.io.IOException;

public class Main {
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
