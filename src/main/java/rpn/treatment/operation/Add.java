package rpn.treatment.operation;

public class Add implements MathCommand<Integer>{
 
    @Override
    public Integer execute(Integer operand1, Integer operand2) {
        return operand1 + operand2;
    }    
}