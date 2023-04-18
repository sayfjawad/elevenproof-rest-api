package nl.multicode.elevenproof.generate.supply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.generate.supplier.exception.NegativeIntegerNotSupportedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FixedLengthStringRandomNumbersSupplierTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 15, 10, 1000})
    @DisplayName("Given the supplier is working correctly"
            + "When supply method is called"
            + "And the argument is a valid positive number"
            + "Then a random number string is generated with the required length")
    void supply_will_supply(int expectedNrOfDigits) {

        final var result = new FixedLengthStringRandomNumbersSupplier(expectedNrOfDigits).supply();
        assertThat(result).hasSize(expectedNrOfDigits);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -9000, -3000000})
    @DisplayName("Given the supplier is working correctly"
            + "When supply method is called"
            + "And the argument is NOT a valid positive number"
            + "Then an exception is thrown containing a message"
            + "And the message explains that negative numbers are not supported")
    void supply_will_throw_exception(int expectedNrOfDigits) {

        assertThatThrownBy(() -> new FixedLengthStringRandomNumbersSupplier(expectedNrOfDigits))
                .isInstanceOf(NegativeIntegerNotSupportedException.class)
                .hasMessageContaining(
                        "Negative integers are not accepted nor supported arguments.");
    }
}