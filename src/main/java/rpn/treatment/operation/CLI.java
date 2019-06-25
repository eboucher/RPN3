package rpn.treatment.operation;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.HashMap;
import java.util.Map;

public class CLI {
     
    private static final Map<Character, MathCommand<Integer>> mapOperations = 
                                                new HashMap<Character, MathCommand<Integer>>();
    
    public CLI() {
        init();
    }
    
    public static void init() {
        mapOperations.put('+', new Add());
        mapOperations.put('*', new Multiply());
        mapOperations.put('-', new Substract());
        mapOperations.put('/', new Divide());
    }
    
    public static Integer calc(Character operator, Integer operand1, Integer operand2) {
        MathCommand<Integer> op = mapOperations.get(operator);
        if (op != null)
            return op.execute(operand1, operand2);
        else
            throw new RuntimeException(operator + "is unsupported");
    }

    static int evaluate(String expression) {
        init();
        String[] split = expression.split("\\s+");
        Stack<Integer> stack = new Stack<>();
        int number1;
        int number2;
        char operator;

        for (String item : split) {
            if(isNumeric(item)) {
                stack.push(Integer.parseInt(item));
            } else {
                number2 = stack.pop();
                number1 = stack.pop();
                operator = item.charAt(0);
                int result = calc(operator, number1, number2);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        int result = evaluate(expression);
        System.out.println("result =  " + result);
    }
}
