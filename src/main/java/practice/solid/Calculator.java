package practice.solid;

public class Calculator {
    private final Operation operation;

    public Calculator(Operation operation) {
        this.operation = operation;
    }

    public double calculate(double[] operands) {
        return operation.perform(operands);
    }
}
