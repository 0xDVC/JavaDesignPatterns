package practice.solid;

import java.util.*;
import java.util.function.Predicate;

public class Runner {
    public static void main(String[] args) {
        // Cool thing: Added B-O-D-M-A-S feature to make it a bit challenging
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the operations you want to perform ( eg: 1 + 3 * .... ): ");
        String expression = input.nextLine().strip();
        String[] arg = expression.split(" ");

        Stack<String> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();

        operands = Arrays.stream(arg)
                .filter(Predicate.not(Runner::isOperator))
                .map(Double::parseDouble)
                .collect(Stack::new, Stack::push, Stack::addAll);

        operators = Arrays.stream(arg)
                .filter(Runner::isOperator)
                .collect(Stack::new, Stack::push, Stack::addAll);

        // still figuring out how to implement B-O-D-M-A-S

    }

    public static boolean isOperator(String operator) {
        return ("+-/*").contains(operator);
    }

    public static Operation getOperation(String operator) {
        return switch (operator) {
            case "+" -> new Addition();
            case "-" -> new Subtraction();
            case "*" -> new Multiplication();
            case "/" -> new Division();
            default -> null;
        };
    }
}
