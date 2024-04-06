package equation;

import exceptions.BracketUnpairedException;
import exceptions.IllegalEquationException;
import exceptions.UnknownOperatorException;
import operator.custom.IOperator;
import operator.custom.OperatorConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;

public class Equation {

    /**
     * 计算自定义运算符表达式的值
     *
     * @param equation 表达式
     * @return 表达式的运算值
     */
    public int evalEquationWithCustomOperator(String equation) {
        //解析表达式
        List<IEquationToken> equationTokens = parseEquation(equation);
        //将表达式转换为逆波兰表达式
        List<IEquationToken> convertedEquationTokens = convert2ReversePolishNotation(equationTokens);
        //验证表达式合法性
        validateEquation(convertedEquationTokens);
        //求值并返回
        return evaluateEquation(convertedEquationTokens);
    }

    /**
     * 将字符串表达式解析为表达式元素
     *
     * @param equation 字符串表达式
     * @return 表达式元素集合
     */
    private List<IEquationToken> parseEquation(String equation) {
        List<IEquationToken> list = new ArrayList<IEquationToken>();

        while (!equation.isBlank()) {
            //去除空格的干扰
            equation = equation.trim();
            //根据枚举定义的Token格式解析表达式
            for (EquationTokenType type : EquationTokenType.values()) {
                Matcher matcher = type.getPattern().matcher(equation);
                if (matcher.find()) {
                    //解析到之后提取对应的内容
                    String temp = matcher.group();
                    //标记类别，加入到元素集合中
                    list.add(new EquationToken(temp, type));
                    //截取掉已经解析的部分，继续后面的解析
                    equation = equation.substring(temp.length());
                }
            }
        }

        return list;
    }

    /**
     * 将表达式转换为逆波兰表达式
     *
     * @param equationItems 表达式元素集合
     * @return 逆波兰表达式顺序的表达式元素集合
     */
    private List<IEquationToken> convert2ReversePolishNotation(List<IEquationToken> equationItems) {
        // 转换后的结果
        List<IEquationToken> list = new ArrayList<>();

        // 转换操作符需要用到的栈
        Stack<IEquationToken> operators = new Stack<>();

        for (IEquationToken item : equationItems) {
            switch (item.getType()) {
                case PARAM:
                    // 当元素是参数时，直接追加到结果中
                    list.add(item);
                    break;
                case OPERATOR:
                    // 当元素是运算符时，如果栈为空或栈顶元素为左括号，则将操作符压栈栈
                    if (operators.empty() || operators.peek().getType() == EquationTokenType.BRACKET_LEFT) {
                        operators.push(item);
                    } else {
                        // 否则，弹出栈顶操作符加入结果中，并把当前操作符压栈
                        list.add(operators.pop());
                        operators.push(item);
                    }
                    break;
                case BRACKET_LEFT:
                    // 左括号直接压栈
                    operators.push(item);
                    break;
                case BRACKET_RIGHT:
                    // 当元素是右括号时，则将栈内的操作符依次弹出并加入结果集合，直到弹出的元素为左括号停止
                    while (true) {
                        // 如果运算符栈为空时该循环还没有跳出，则说明有括号不匹配的问题
                        if (operators.empty()) {
                            throw new BracketUnpairedException();
                        }

                        IEquationToken operator = operators.pop();
                        if (EquationTokenType.BRACKET_LEFT == operator.getType()) {
                            break;
                        }
                        list.add(operator);
                    }
                    break;
                default:
                    break;
            }
        }

        // 将栈内剩余的操作符依次弹出加入结果
        while (!operators.empty()) {
            IEquationToken item = operators.pop();

            //最终运算符里如果包含括号，则有括号不匹配的问题
            if (EquationTokenType.BRACKET_LEFT == item.getType()) {
                throw new BracketUnpairedException();
            }

            list.add(item);
        }

        return list;
    }

    /**
     * 表达式求值
     *
     * @param equationItems 逆波兰表达式格式的表达式元素集合
     * @return 表达式的最终值
     */
    private int evaluateEquation(List<IEquationToken> equationItems) {
        // 计算中用来存储中间数值的栈
        Stack<Integer> params = new Stack<>();

        for (IEquationToken item : equationItems) {
            switch (item.getType()) {
                case PARAM:
                    // 当元素是参数时，直接压栈
                    params.push(Integer.parseInt(item.getValue()));
                    break;
                case OPERATOR:
                    // 当元素时操作符时，弹出栈顶的两个参数，进行计算，并把计算结果重新压栈
                    String operatorStr = item.getValue();

                    int paramA = params.pop();
                    int paramB = params.pop();

                    OperatorConsumer consumer = new OperatorConsumer();
                    IOperator operator = consumer.getOperator(operatorStr);

                    if (null == operator) {
                        throw new UnknownOperatorException(operatorStr);
                    }

                    int result = operator.calculate(paramB, paramA);
                    params.push(result);
                    break;
                default:
                    break;
            }
        }

        //最后留在栈内的一个元素就是表达式的最终结果
        return params.pop();
    }

    /**
     * 验证表达式是否合法：表达式中参数的数量要是运算符的数量+1
     *
     * @param equationItems 转换为逆波兰表达式的元素集合
     */
    private void validateEquation(List<IEquationToken> equationItems) {
        int paramCount = 0;
        int operatorCount = 0;
        for (IEquationToken item : equationItems) {
            switch (item.getType()) {
                case PARAM:
                    paramCount++;
                    break;
                case OPERATOR:
                    operatorCount++;
                    break;
                default:
                    break;
            }
        }

        if (paramCount != operatorCount + 1) {
            throw new IllegalEquationException();
        }
    }

}
