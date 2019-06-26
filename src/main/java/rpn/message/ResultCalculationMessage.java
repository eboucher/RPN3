package rpn.message;

import java.util.Stack;

public class ResultCalculationMessage implements Message {
    public static final String MESSAGE_TYPE = "result";

    public final Stack<Double> result;
    public final String expressionId;

    public ResultCalculationMessage(Stack<Double> result, String expressionId) {
        this.result = result;
        this.expressionId = expressionId;
    }

    @Override
    public String messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public String getToken() {
        return null;
    }
}