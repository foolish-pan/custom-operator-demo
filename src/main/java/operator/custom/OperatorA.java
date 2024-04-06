package operator.custom;

/**
 * 自定义运算符A
 */
public class OperatorA implements IOperator {

    @Override
    public int calculate(int a, int b) {
        return a * 2 + b;
    }

}
