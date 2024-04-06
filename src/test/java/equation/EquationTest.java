package equation;

import exceptions.BracketUnpairedException;
import exceptions.IllegalEquationException;
import exceptions.UnknownOperatorException;
import org.junit.Assert;
import org.junit.Test;


public class EquationTest {

    Equation equationParser = new Equation();

    @Test()
    public void equationTestOperateA() {
        int caseRes1 = equationParser.evalEquationWithCustomOperator("3a1");
        Assert.assertEquals(7, caseRes1);
    }

    @Test()
    public void equationTestOperateB() {
        int caseRes2 = equationParser.evalEquationWithCustomOperator("3b2");
        Assert.assertEquals(-4, caseRes2);
    }

    @Test()
    public void equationTestOperateC() {
        int caseRes3 = equationParser.evalEquationWithCustomOperator("3c1");
        Assert.assertEquals(8, caseRes3);
    }

    @Test()
    public void equationTestOperateMix() {
        int caseRes4 = equationParser.evalEquationWithCustomOperator("5a7c3b4a8");
        Assert.assertEquals(2256, caseRes4);
    }

    @Test()
    public void equationTestWithSpace() {
        int caseRes5 = equationParser.evalEquationWithCustomOperator("5  a7c 3b4   a8");
        Assert.assertEquals(2256, caseRes5);
    }

    @Test()
    public void equationTestWithMultiBit() {
        int caseRes6 = equationParser.evalEquationWithCustomOperator("4 b 3 c 532 a 1");
        Assert.assertEquals(-1045, caseRes6);
    }

    @Test()
    public void equationTestWithBrackets() {
        int caseRes7 = equationParser.evalEquationWithCustomOperator("4 b (3 c 532) a 1");
        Assert.assertEquals(1047, caseRes7);
    }

    @Test()
    public void equationTestWithUnknownOperator() {
        Assert.assertThrows(UnknownOperatorException.class, () -> equationParser.evalEquationWithCustomOperator("4 d 1"));
    }

    @Test()
    public void equationTestWithBracketUnpaired() {
        Assert.assertThrows(BracketUnpairedException.class, () -> equationParser.evalEquationWithCustomOperator("4 d 1)"));
        Assert.assertThrows(BracketUnpairedException.class, () -> equationParser.evalEquationWithCustomOperator("(4 d 1"));
        Assert.assertThrows(BracketUnpairedException.class, () -> equationParser.evalEquationWithCustomOperator("4 (b (3 c 532) a 1"));
    }

    @Test()
    public void equationTestWithIllegalEquation() {
        Assert.assertThrows(IllegalEquationException.class, () -> equationParser.evalEquationWithCustomOperator("5a7c3b4a"));
    }

}
