package org.calculator;

import java.util.Queue;
import java.util.Stack;

public class PostfixNotationEvaluator
{
    static double evaluatePostfixExpression(Queue<String> expression)
    {
        Stack<Double> stack = new Stack<>();

        for (String token : expression) {
            if (Helper.isNumber(token)) {
                stack.push(Double.parseDouble(token));
            }  else {
                double operand1 = stack.pop();
                double operand2 = stack.pop();

                switch(token) {
                    case "+":
                        stack.push(operand2 + operand1);
                        break;
                    case "-":
                        stack.push(operand2 - operand1);
                        break;
                    case "/":
                        stack.push(operand2 / operand1);
                        break;
                    case "*":
                        stack.push(operand2 * operand1);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
