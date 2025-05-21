package ascii_art;

import ascii_art.exceptions.InvalidResCommandException;
import image.Image;

class ResHandler implements IParamHandler {

    private String charsPerRow;

    public ResHandler(int charsPerRow) {
        this.charsPerRow = String.valueOf(charsPerRow);
    }

    private void handleResUpCommand(Image img) throws InvalidResCommandException {
        int resInt = Integer.parseInt(charsPerRow);
        resInt *= 2;
        if (resInt > img.getWidth()) {
            throw new InvalidResCommandException();
        }
        charsPerRow = String.valueOf(resInt);
    }

    private void handleResDownCommand(Image img) throws InvalidResCommandException {
        int resInt = Integer.parseInt(charsPerRow);
        int minValue = Math.max(img.getWidth() / img.getHeight(), 1);
        resInt /= 2;
        if (resInt < minValue) {
            throw new InvalidResCommandException();
        }
        charsPerRow = String.valueOf(resInt);
    }

    @Override
    public String get() {
        return charsPerRow;
    }

    public int getInt() {
        return Integer.parseInt(charsPerRow);
    }

    public void handleCommand(String[] args, Image img) throws InvalidResCommandException {

        if (args.length < 2) {
            System.out.println(charsPerRow);
            return;
        }

        String arg = args[1];
        if (arg.equals("up")) {
            handleResUpCommand(img);
        } else if (arg.equals("down")) {
            handleResDownCommand(img);
        } else {
            throw new InvalidResCommandException();
        }
    }
}
