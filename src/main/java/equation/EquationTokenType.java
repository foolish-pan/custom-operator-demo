package equation;

import java.util.regex.Pattern;

public enum EquationTokenType {

    /**
     * 参数
     */
    PARAM("^\\d+"),

    /**
     * 运算符
     */
    OPERATOR("^[a-z]+"),

    /**
     * 左括号
     */
    BRACKET_LEFT("^\\("),

    /**
     * 右括号
     */
    BRACKET_RIGHT("^\\)");

    private final Pattern pattern;

    EquationTokenType(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
