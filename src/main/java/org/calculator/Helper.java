package org.calculator;

public class Helper
{
    static boolean isNumber(String token)
    {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static boolean isUnaryMinus(int pos, String expression)
    {
        boolean isUnaryMinus = false;

        boolean prevTokenIsNotDigit = !Character.isDigit(expression.charAt(pos - 1));
        boolean prevTokenIsNotCloseBracket = !(expression.charAt(pos - 1) == ')');
        boolean nextTokenIsDigit = Character.isDigit(expression.charAt(pos + 1));

        if ((pos - 1 >= 0)
                && prevTokenIsNotDigit
                && prevTokenIsNotCloseBracket
                && nextTokenIsDigit) {
            return true;
        }

        return isUnaryMinus;
    }
}
