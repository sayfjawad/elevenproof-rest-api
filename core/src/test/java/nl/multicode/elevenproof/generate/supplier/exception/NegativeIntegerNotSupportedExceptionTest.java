package nl.multicode.elevenproof.generate.supplier.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NegativeIntegerNotSupportedExceptionTest {

    @Test
    void givenExceptionIsThrownThenTheMessageContainsDescriptiveMessage() {

        final var exception = new NegativeIntegerNotSupportedException();

        try {
            throw exception;
        } catch (NegativeIntegerNotSupportedException ex) {
            assertThat(ex.getMessage()).isEqualTo(
                    "Negative integers are not accepted nor supported arguments.");
        }
    }
}