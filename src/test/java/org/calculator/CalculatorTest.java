package org.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest
{
    public void testCalculate_SimpleExpression_RightAnswer() throws Exception
    {
        String expression = "1+1-1*1/1";
        Double expressionAnswer = 1.0;
        double calculatorAnswer = Calculator.calculate(expression);

        assertEquals(expressionAnswer, calculatorAnswer);
    }

    public void testCalculate_SimpleExpressionWithBrackets_RightAnswer() throws Exception
    {
        String expression = "(1+1)-(1*1)/1";
        Double expressionAnswer = 1.0;
        double calculatorAnswer = Calculator.calculate(expression);

        assertEquals(expressionAnswer, calculatorAnswer);
    }

    public void testCalculate_SimpleExpressionWithoutOpenBracket_Exception()
    {
        String expression = "1+1+1)";

        Throwable thrown = assertThrows(Exception.class, () -> Calculator.calculate(expression));
        assertEquals("This expression is invalid", thrown.getMessage());
    }

    public void testCalculate_SimpleExpressionWithoutCloseBracket_Exception()
    {
        String expression = "1+(1+1";

        Throwable thrown = assertThrows(Exception.class, () -> Calculator.calculate(expression));
        assertEquals("This expression is invalid", thrown.getMessage());
    }

    public void testCalculate_SimpleExpressionWithUnaryMinus_RightAnswer() throws Exception
    {
        String expression = "4*-7";
        Double expressionAnswer = -28.0;

        double calculatorAnswer = Calculator.calculate(expression);

        assertEquals(expressionAnswer, calculatorAnswer);
    }

    public void testCalculate_SimpleExpressionWithTwoOperatorsInARow_Exception()
    {
        String expression = "3+*4";
        Throwable thrown = assertThrows(Exception.class, () -> Calculator.calculate(expression));
        assertEquals("This expression is invalid", thrown.getMessage());
    }

    public void testCalculate_EmptyExpression_Exception()
    {
        String expression = "";
        Throwable thrown = assertThrows(Exception.class, () -> Calculator.calculate(expression));
        assertEquals("Expression is empty", thrown.getMessage());
    }

    public void testCalculate_ExpressionContainsInvalidCharacter_Exception()
    {
        String expression = "a1+1";
        Throwable thrown = assertThrows(Exception.class, () -> Calculator.calculate(expression));
        assertEquals("Expression contains invalid character", thrown.getMessage());
    }
}
