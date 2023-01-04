package org.calculator;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        while(true) {
            System.out.println("Enter your math expression:");
            Scanner in = new Scanner(System.in);

            String expression = in.nextLine();

            try {
                double expressionsResult = Calculator.calculate(expression);
                int numberOfNumbersInARow = Calculator.getNumberOfNumbersInExpression(expression);

                System.out.println("Answer: " + expressionsResult);
                System.out.println("Number of numbers in an expression: " + numberOfNumbersInARow);
                System.out.println();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
