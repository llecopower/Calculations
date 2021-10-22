package com.lunaticaliens.calculations.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;

/**
 * The type Evaluation class.
 */
public class EvaluationClass {
    private int numberOfTokens = 0;
    private Context context;

    /**
     * Instantiates a new Evaluation class.
     *
     * @param context the context
     */
    public EvaluationClass(Context context) {
        this.context = context;
    }

    /**
     * Evaluate expression double.
     *
     * @param expression the expression
     * @return the double
     */
    public Double evaluateExpression(String expression) {
        String[] tokens = stringToTokens(expression);
        String[] postfix = tokenToPostfix(tokens);

        return evaluatePostfix(postfix);

    }

    /**
     * Convert string to Tokens
     * @param expression the string expression
     * @return an array of tokens
     */
    private String[] stringToTokens(String expression) {

        String[] numbers = expression.split("[+*/-]");
        String[] operators = expression.split("[0-99999]+[.]*[0-99999]*");
        String[] tokens = new String[100];

        int j = 0;
        for (int i = 0; i < operators.length - 1; i++) {
            tokens[j] = numbers[i];
            tokens[j + 1] = operators[i + 1];
            j += 2;
            numberOfTokens += 2;
        }
        tokens[j] = numbers[numbers.length - 1];
        numberOfTokens++;

        return tokens;

    }

    /**
     * Converts the tokens to Postfix expression
     * @param tokens the tokens
     * @return post expression
     */
    private String[] tokenToPostfix(String[] tokens) {
        String[] postfix = new String[100];
        Stack<String> stack = new Stack<>();
        int j = 0;

        for (int i = 0; i < numberOfTokens; i++) {
            boolean isOperator = false;
            if (tokens != null)
                if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                    isOperator = true;
                }

            if (!isOperator) {
                assert tokens != null;
                postfix[j] = tokens[i];
                j++;
            } else {
                if (stack.empty()) {
                    stack.push(tokens[i]);
                } else {
                    if (precedence(tokens[i]) > precedence(stack.peek())) // token > stack
                    {
                        stack.push(tokens[i]);
                    } else // stack >= token
                    {
                        while (precedence(stack.peek()) >= precedence(tokens[i])) {
                            postfix[j] = stack.pop();
                            j++;
                            if (stack.empty())
                                break;
                        }

                        stack.push(tokens[i]);
                    }
                }
            }

        }

        while (!stack.empty()) {
            postfix[j] = stack.pop();
            j++;
        }

        return postfix;

    }

    /**
     * find the precedence of the operator
     * @param s the operator
     * @return integer value
     */
    private static int precedence(String s) {
        int precedence = 0;

        switch (s) {
            case "/":
                precedence = 4;
                break;
            case "*":
                precedence = 3;
                break;
            case "+":
                precedence = 2;
                break;
            case "-":
                precedence = 1;
                break;
        }
        return precedence;

    }

    /**
     * Evaluate the postfix expression
     * @param postfix the postfix expression
     * @return a value
     */
    private Double evaluatePostfix(String[] postfix) {
        Stack<Double> stack = new Stack<>();
        double result = 0.0;

        for (int i = 0; i < numberOfTokens; i++) {
            boolean isOperator = false;

            if (postfix[i].equals("+") || postfix[i].equals("-") || postfix[i].equals("*") || postfix[i].equals("/")) {
                isOperator = true;
            }

            if (!isOperator) // if number
            {
                try {

                    stack.push(Double.parseDouble(postfix[i]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error while parsing", Toast.LENGTH_SHORT).show();
                    return 0.0;
                }
            } else {
                Double second = stack.pop();
                Double first = stack.pop();

                if (postfix[i].equals("+")) {
                    result = first + second;
                }

                if (postfix[i].equals("-")) {
                    result = first - second;
                }

                if (postfix[i].equals("*")) {
                    result = first * second;
                }

                if (postfix[i].equals("/")) {
                    result = first / second;
                }
                stack.push(result);
            }
        }
        return result;
    }

    /**
     * Generate expression string.
     *
     * @return the string
     */
    public String generateExpression() {
        String ex = "";

        Random random = new Random();
        int firstDiget = random.nextInt(99);
        int secondDigit = random.nextInt(99);
        int operator = random.nextInt(4);

        char operatorChar = ' ';
        switch (operator) {
            case 0:
                operatorChar = '+';
                break;
            case 1:
                operatorChar = '-';
                break;
            case 2:
                operatorChar = '*';
                break;
            case 3:
                operatorChar = '/';
                break;
            default:
                operatorChar = '+';
        }
        ex = Integer.toString(firstDiget) + operatorChar + Integer.toString(secondDigit);

        return ex;
    }
}