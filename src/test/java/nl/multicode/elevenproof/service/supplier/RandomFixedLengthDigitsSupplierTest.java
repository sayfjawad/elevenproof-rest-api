package nl.multicode.elevenproof.service.supplier;

import static org.assertj.core.api.Assertions.assertThat;

import nl.multicode.elevenproof.generator.supplier.RandomFixedLengthDigitsSupplier;
import org.junit.jupiter.api.Test;

class RandomFixedLengthDigitsSupplierTest {

  public static final int EXPECTED_NR_OF_DIGITS = 9;

  @Test
  void supply() {

    final var result = new RandomFixedLengthDigitsSupplier(EXPECTED_NR_OF_DIGITS).supply();
    assertThat(result).hasSize(EXPECTED_NR_OF_DIGITS);
  }
}