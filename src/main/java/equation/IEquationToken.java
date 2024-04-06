package equation;

/**
 * 表达式元素定义
 */
public interface IEquationToken {

    String getValue();

    EquationTokenType getType();

}
