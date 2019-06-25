package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.treatment.operation.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
    }

    @Test
    public void should_evaluate_simple_division() {
        assertThat(evaluate("30 5 /")).isEqualTo(6);
    }

    @Test
    public void should_evaluate_more_complex_addition_with_middle_operand() {
        assertThat(evaluate("5 2 3 + -")).isEqualTo(0);
    }

    @Test
    public void should_evaluate_more_complex_addition_with_middle_operand_more_complex() {
        assertThat(evaluate("4 2 + 3 -")).isEqualTo(3);
    }

    @Test
    public void should_evaluate_more_complex_addition_with_more_complex_intermediate_operations() {
        assertThat(evaluate("3 5 8 * 7 + *")).isEqualTo(141);
    }

    @Test
    public void should_evaluate_more_complex_with_negative_number() {
        assertThat(evaluate("3 5 -8 * 7 + *")).isEqualTo(-99);
    }
}