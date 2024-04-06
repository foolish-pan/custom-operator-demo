package equation;

public class EquationToken implements IEquationToken{


    private final String value;

    private final EquationTokenType type;

    public EquationToken(String value, EquationTokenType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public EquationTokenType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
