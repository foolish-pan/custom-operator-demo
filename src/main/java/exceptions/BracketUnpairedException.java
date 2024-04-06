package exceptions;

public class BracketUnpairedException extends RuntimeException {

    public BracketUnpairedException() {
        super("Bracket unpaired");
    }

}
