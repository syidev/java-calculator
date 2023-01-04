package org.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Calculator
{
    private static void simpleExpressionValidation(String expression) throws Exception
    {
        if (expression.isEmpty()) {
            throw new Exception("Expression is empty");
        }

        String invalidCharactersRegexp = "(.*)[a-z](.*)";
        boolean isMatches = expression.matches(invalidCharactersRegexp);

        if (isMatches) {
            throw new Exception("Expression contains invalid character");
        }
    }

    public static double calculate(String expression) throws Exception
    {
        double value;
        try {
            simpleExpressionValidation(expression);
            Queue<String> postfixExpression = PostfixNotationConverter.convertToPostfixNotation(expression);
            value = PostfixNotationEvaluator.evaluatePostfixExpression(postfixExpression);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return value;
    }
    public static int getNumberOfNumbersInExpression(String expression)
    {
        List<String> list = new ArrayList<>();

         for (int pos = 0; pos < expression.length(); pos++) {
            String token = Character.toString(expression.charAt(pos));

            if (Objects.equals(token, "-") && Helper.isUnaryMinus(pos, expression)) {
                int begin = pos;
                while (pos + 1 < expression.length()
                        && (Character.isDigit(expression.charAt(pos + 1))
                            || Objects.equals(expression.charAt(pos + 1), '.'))) {
                    pos++;
                }
                list.add(expression.substring(begin, pos + 1));
            } else if (Helper.isNumber(token)) {
                int begin = pos;
                while (pos < expression.length()
                        && (Character.isDigit(expression.charAt(pos))
                        || Objects.equals(expression.charAt(pos), '.'))) {
                    pos++;
                }
                list.add(expression.substring(begin, pos));
                pos--;
            }
        }
         return list.size();
    }
}
