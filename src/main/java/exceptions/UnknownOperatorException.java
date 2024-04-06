package exceptions;

public class UnknownOperatorException extends RuntimeException {
    
    public UnknownOperatorException(String message) {
        super(String.format("Unknown operator: %s", message));
    }

}
