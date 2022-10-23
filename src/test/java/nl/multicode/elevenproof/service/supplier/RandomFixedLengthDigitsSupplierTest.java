package nl.multicode.elevenproof.service.supplier;

import static org.assertj.core.api.Assertions.assertThat;

import nl.multicode.elevenproof.generator.supplier.RandomFixedLengthDigitsSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomFixedLengthDigitsSupplierTest {

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 3, 15, 10})
  void supply(int expectedNrOfDigits) {

    final var result = new RandomFixedLengthDigitsSupplier(expectedNrOfDigits).supply();
    assertThat(result).hasSize(expectedNrOfDigits);
  }
}