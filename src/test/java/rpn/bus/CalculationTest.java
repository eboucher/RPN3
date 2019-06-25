package rpn.bus;

import org.assertj.core.data.Offset;
import org.junit.Test;
import rpn.consumer.Calculator;
import rpn.consumer.TokenizerConsumer;
import rpn.message.*;


public class CalculationTest {

    private final ConsumerForTest consumerTest = new ConsumerForTest();

    private InMemoryBus createTestBus() {
        InMemoryBus bus = new InMemoryBus();

        bus.subscribe(ExpressionMessage.MESSAGE_TYPE, new TokenizerConsumer(bus));

        Calculator calculator = new Calculator(bus);
        bus.subscribe(TokenMessage.MESSAGE_TYPE, calculator);
        bus.subscribe(OperatorMessage.MESSAGE_TYPE, calculator);
        bus.subscribe(EndOfToken.MESSAGE_TYPE, calculator);

        bus.subscribe(ResultCalculationMessage.MESSAGE_TYPE, consumerTest);

        return bus;
    }
}