package ascii_art;

import java.util.Arrays;

import ascii_art.exceptions.InvalidCharAddException;
import ascii_art.exceptions.InvalidCharOperationException;
import ascii_art.exceptions.InvalidCharRemoveException;
import ascii_art.exceptions.ParamException;
import image.Image;
import image_char_matching.SubImgCharMatcher;

/**
 * Manages the set of characters used for ASCII art generation.
 * Provides functionality to add, remove, and manipulate the character set
 * through various commands.
 */
public class CharSet implements IParamHandler {

    private SubImgCharMatcher matcher;
    /**
     * Enumeration for character operations (add or remove)
     */
    enum Op {
        REMOVE, ADD
    }

    /**
     * Constructs a CharSet with default characters (digits 0-9)
     */
    public CharSet(SubImgCharMatcher matcher) {
        this.matcher = matcher;
        handleRange("0-9", Op.ADD);

    }



    /**
     * Handles character operations based on operation type and input.
     * 
     * @param input the input string specifying which characters to operate on
     * @param op    the operation type (ADD or REMOVE)
     * @throws InvalidCharOperationException if the operation is invalid
     */
    public void handleOp(String input, Op op) throws InvalidCharOperationException {
        if (input.equals("all")) {
            handleAllChars(op);
        } else if (input.length() == 1) {
            operate(op, input.charAt(0));
        } else if (input.matches(".-.")) {
            handleRange(input, op);
        } else {
            if (Op.REMOVE.equals(op)) {
                throw new InvalidCharRemoveException();
            } else {
                throw new InvalidCharAddException();
            }
        }

    }


    /**
     * Prints the characters in the set in sorted order.
     */
    public void printChars() {

        // Sort the characters before printing
        char[] sortedChars = new char[matcher.getChars().size()];
        int i = 0;
        for (char c : matcher.getChars()) {
            sortedChars[i++] = c;
        }

        Arrays.sort(sortedChars);

        for (char c : sortedChars) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Handles commands for the character set.
     * 
     * @param args command arguments
     * @param img  the image (not used in this handler)
     * @throws ParamException if the parameters are invalid
     */
    @Override
    public void handleCommand(String[] args, Image img) throws ParamException {
        if (args.length < 2) {
            if (args[0].equals("chars")) {
                printChars();
            } else {
                throw new ParamException();
            }
        } else if (args[0].equals("remove")) {
            remove(args);
        } else if (args[0].equals("add")) {
            add(args);
        }
    }

    /**
     * Handles the remove command.
     * 
     * @param args command arguments
     */
    private void remove(String[] args) {
        try {
            if (args.length < 2) {
                throw new InvalidCharRemoveException();
            }
            handleOp(args[1], Op.REMOVE);
        } catch (InvalidCharOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Handles the add command.
     * 
     * @param args command arguments
     */
    private void add(String[] args) {
        try {
            if (args.length < 2) {
                throw new InvalidCharAddException();
            }
            String input = args[1];
            handleOp(input, Op.ADD);
        } catch (InvalidCharOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Performs the specified operation (add or remove) on a single character.
     * 
     * @param op the operation type
     * @param c  the character to operate on
     */
    private void operate(Op op, char c) {
        if (op == Op.REMOVE)
            matcher.removeChar(c);
        else
            matcher.addChar(c);
    }

    /**
     * Handles operations on all printable ASCII characters.
     * 
     * @param op the operation type
     */
    private void handleAllChars(Op op) {
        for (char c = 32; c < 127; c++) {
            operate(op, c);
        }
    }

    /**
     * Handles operations on a range of characters.
     * 
     * @param range string representing a character range (e.g., "a-z")
     * @param op    the operation type
     */
    private void handleRange(String range, Op op) {
        char start = range.charAt(0);
        char end = range.charAt(2);
        if (start > end) {
            char temp = start;
            end = start;
            start = temp;
        }
        for (char c = start; c <= end; c++) {
            operate(op, c);
        }
    }

    /**
     * Returns the current character set as an array of chars.
     * 
     * @return array containing all characters in the set
     */
    public char[] getChars() {
        char[] charArray = new char[matcher.getChars().size()];
        int index = 0;
        for (Character c : matcher.getChars()) {
            charArray[index++] = c;
        }
        return charArray;
    }
}
