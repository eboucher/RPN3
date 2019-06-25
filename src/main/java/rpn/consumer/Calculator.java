package rpn.consumer;

import rpn.bus.Bus;
import rpn.message.*;
import rpn.utils.ParseDouble;

import java.util.Stack;

public class Calculator implements Consumer {

    private Stack<Double> stackNumber = new Stack<>();
    private final Bus bus;

    public Calculator(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {

        if(message instanceof TokenMessage) {
            TokenMessage tokenMessage = (TokenMessage) message;
            Double elementTmp = Double.parseDouble(tokenMessage.getToken());

            if (elementTmp != null) stackNumber.push(elementTmp);
            else bus.publish(new OperationMessage(tokenMessage.getToken(), stackNumber, tokenMessage.getExpressionId()));
        }
        else if(message instanceof OperatorMessage) {
            OperatorMessage operatorMessage = (OperatorMessage) message;
            stackNumber.addAll(operatorMessage.stackNumbers);
        }
        else if(message instanceof EndOfToken) {
            EndOfToken endOfToken = (EndOfToken) message;
            bus.publish(new ResultCalculationMessage(stackNumber, endOfToken.expressionId));
        }
    }
}
