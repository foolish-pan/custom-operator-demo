package operator.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * 运算符统一注册
 */
public class OperatorConsumer {

    private static final Map<String, IOperator> operators = new HashMap<String, IOperator>();

    static {
        operators.put("a", new OperatorA());
        operators.put("b", new OperatorB());
        operators.put("c", new OperatorC());
    }

    public IOperator getOperator(String operate) {
        return operators.get(operate);
    }


}
