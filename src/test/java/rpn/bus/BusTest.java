package rpn.bus;

import org.assertj.core.data.Offset;
import org.junit.Test;
import rpn.consumer.Calculator;
import rpn.consumer.TokenizerConsumer;
import rpn.message.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

public class BusTest {

    private final ConsumerForTest consumerTest = new ConsumerForTest();

    private InMemoryBus createTestBus() {
        InMemoryBus bus = new InMemoryBus();

        bus.subscribe(ResultCalculationMessage.MESSAGE_TYPE, consumerTest);

        return bus;
    }

    private final InMemoryBus bus = createTestBus();

    @Test
    public void should_evaluate_two_different_expressions() {
        bus.subscribe(ExpressionMessage.MESSAGE_TYPE, new TokenizerConsumer(bus));
        Calculator calculator = new Calculator(bus);
        bus.subscribe(TokenMessage.MESSAGE_TYPE, calculator);
        bus.subscribe(EndOfToken.MESSAGE_TYPE, calculator);

        bus.publish(new ExpressionMessage("0", UUID.randomUUID().toString()));

        Offset<Double> offsetMillionthAccuracy = Offset.offset(0.00001);
        assertThat(consumerTest.getStackResult().pop()).isCloseTo(0, offsetMillionthAccuracy);

        bus.publish(new ExpressionMessage("1", UUID.randomUUID().toString()));
        assertThat(consumerTest.getStackResult().pop()).isCloseTo(1, offsetMillionthAccuracy);
    }

    @Test
    public void should_print_invalid_message_token_exception() {
        bus.publish(new ExpressionMessage("0", UUID.randomUUID().toString()));
    }
}
