package practice.solid;

import java.util.Arrays;

/**
 * Let's walk through my calculator:
 * It receives inputs of this form [ operand, operator, operand, .... ]
 * Now, it comes in as a string. I'll parse the string to the appropriate type of data and operand and perform the operation on it.    * Display the output.
**/

public interface Operation {
    double perform(double[] operands);
}

class Addition implements Operation {
    public double perform(double[] operands) {
        return Arrays.stream(operands).sum();
    }
}

class Subtraction implements Operation{
    public double perform(double[] operands) {
        double result = operands[0];

        for(int i =1; i < operands.length; i++) {
            result -= operands[i];
        }
        return result;
    }
}

class Division implements Operation {
    public double perform(double[] operands) {
        double result = operands[0];

        for(int i =1; i < operands.length; i++) {
            result /= operands[i];
        }
        return result;
    }
}

class Multiplication implements Operation {
    public double perform(double[] operands) {
        return Arrays.stream(operands).reduce(1, (a,b) -> a*b);
    }
}
