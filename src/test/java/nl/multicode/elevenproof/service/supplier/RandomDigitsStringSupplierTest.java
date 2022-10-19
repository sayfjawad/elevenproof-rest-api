package nl.multicode.elevenproof.service.supplier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomDigitsStringSupplierTest {

    public static final int EXPECTED_NR_OF_DIGITS = 9;

    @Test
    void supply() {
        final var result = new RandomDigitsStringSupplier(EXPECTED_NR_OF_DIGITS).supply();
        assertThat(result.length()).isEqualTo(EXPECTED_NR_OF_DIGITS);
    }
}