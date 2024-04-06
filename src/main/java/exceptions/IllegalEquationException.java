package exceptions;

public class IllegalEquationException extends RuntimeException {

    public IllegalEquationException() {
        super("Illegal equation that operator has incorrect params");
    }

}
