package org.calculator;

import java.util.*;

public class PostfixNotationConverter
{
    private static final ArrayList<String> operators = new ArrayList<>()
    {
        {
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };

    private static boolean isOperator(String token)
    {
        return operators.contains(token);
    }

    private static boolean isLeftAssociativeOperator(String token)
    {
        return operators.contains(token);
    }

    private static int getPrecedence(String token)
    {
        if (Objects.equals(token, "+") || Objects.equals(token, "-")) {
            return 1;
        } else if (Objects.equals(token, "*") || Objects.equals(token, "/")) {
            return 2;
        } else {
            return -1;
        }
    }

    static Queue<String> convertToPostfixNotation(String expression) throws Exception
    {
        Stack<String> stack = new Stack<>();
        Queue<String> output = new LinkedList<>();

        String prevToken = "";
        for (int pos = 0; pos < expression.length(); pos++) {
            String token = Character.toString(expression.charAt(pos));

            if (Objects.equals(token, "-") && Helper.isUnaryMinus(pos, expression)) {
                int begin = pos;
                while (pos + 1 < expression.length()
                        && (Character.isDigit(expression.charAt(pos + 1))
                            || Objects.equals(expression.charAt(pos + 1), '.'))) {
                    pos++;
                }
                output.add(expression.substring(begin, pos + 1));
                continue;
            }
            if (Helper.isNumber(token)) {
                int begin = pos;
                while (pos < expression.length()
                        && (Character.isDigit(expression.charAt(pos))
                        || Objects.equals(expression.charAt(pos), '.'))) {
                    pos++;
                }
                output.add(expression.substring(begin, pos));
                pos--;
            } else if (isOperator(token)) {
                if (!Objects.equals(token, "-") && isOperator(prevToken)) {
                    throw new Exception("This expression is invalid");
                }
                while(!stack.isEmpty()
                        && getPrecedence(token) <= getPrecedence(stack.peek())
                        && isLeftAssociativeOperator(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (Objects.equals(token, "(")) {
                stack.push(token);
            } else if (Objects.equals(token, ")")) {
                while (!stack.isEmpty() && !Objects.equals(stack.peek(), "(")) {
                    output.add(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new Exception("This expression is invalid");
                }
                stack.pop();
            }

            prevToken = token;
        }

        while (!stack.isEmpty()) {
            if (Objects.equals(stack.peek(), "(")) {
                throw new Exception("This expression is invalid");
            }
            output.add(stack.pop());
        }

        return output;
    }
}
