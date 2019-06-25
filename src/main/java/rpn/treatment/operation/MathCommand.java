package rpn.treatment.operation;

public interface MathCommand<E> {
    abstract E execute(E operand1, E operand2);
}