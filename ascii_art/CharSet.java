package ascii_art;

import java.util.HashSet;
import java.util.Set;
import java.text.CharacterIterator;
import java.util.Arrays;

import ascii_art.exceptions.InvalidCharAddException;
import ascii_art.exceptions.InvalidCharOpeartionException;
import ascii_art.exceptions.InvalidCharRemoveException;
import ascii_art.exceptions.ParamException;
import image.Image;

public class CharSet implements IParamHandler {

    private Set<Character> chars;

    enum Op {
        REMOVE, ADD
    }

    public CharSet() {
        chars = new HashSet<>();
        handleRange("0-9", Op.ADD);
    }

    public void handleOp(String input, Op op) throws InvalidCharOpeartionException {
        if (input.equals("all")) {
            handleAllChars(op);
        } else if (input.length() == 1) {
            operate(op, input.charAt(0));
        } else if (input.matches("[a-zA-Z0-9]-[a-zA-Z0-9]")) {
            handleRange(input, op);
        } else {
            if (Op.REMOVE.equals(op)) {
                throw new InvalidCharRemoveException();
            } else {
                throw new InvalidCharAddException();
            }
        }

    }

    @Override
    public String get() {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void printChars() {

        // Sort the characters before printing
        char[] sortedChars = new char[chars.size()];
        int i = 0;
        for (char c : chars) {
            sortedChars[i++] = c;
        }

        Arrays.sort(sortedChars);

        for (char c : sortedChars) {
            System.out.print(c + " ");
        }

    }

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

    private void remove(String[] args) {
        try {
            if (args.length < 2) {
                throw new InvalidCharRemoveException();
            }
            handleOp(args[1], Op.REMOVE);
        } catch (InvalidCharOpeartionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void add(String[] args) {
        try {
            if (args.length < 2) {
                throw new InvalidCharAddException();
            }
            String input = args[1];
            handleOp(input, Op.ADD);
        } catch (InvalidCharOpeartionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void operate(Op op, char c) {
        if (op == Op.REMOVE)
            chars.remove(c);
        else
            chars.add(c);
    }

    private void handleAllChars(Op op) {
        for (char c = 32; c < 127; c++) {
            operate(op, c);
        }
    }

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

    public char[] getChars() {
        // didnt found a better way to convert set to char array
        char[] charArray = new char[chars.size()];
        int index = 0;
        for (Character c : chars) {
            charArray[index++] = c;
        }
        return charArray;
    }
}
