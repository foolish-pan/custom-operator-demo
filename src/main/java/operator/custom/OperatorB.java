package operator.custom;

/**
 * 自定义运算符B
 */
public class OperatorB implements IOperator {

    @Override
    public int calculate(int a, int b) {
        return (a - 5) * b;
    }

}
