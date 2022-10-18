package nl.multicode.elevenproof.service.supplier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Random9DigitsStringSupplierTest {

    public static final int EXPECTED_NR_OF_DIGITS = 9;

    @Test
    void supply() {
        final var result = new Random9DigitsStringSupplier().supply();
        assertThat(result.length()).isEqualTo(EXPECTED_NR_OF_DIGITS);
    }
}