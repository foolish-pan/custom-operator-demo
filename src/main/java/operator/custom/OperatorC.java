package operator.custom;


/**
 * 自定义运算符C
 */
public class OperatorC implements IOperator {

    @Override
    public int calculate(int a, int b) {
        return a * a - b;
    }
}
