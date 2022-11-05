package nl.multicode.elevenproof.generate.supply;

import java.util.Random;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.generate.supplier.exception.NegativeIntegerNotSupportedException;

public class FixedLengthRandomNumbersStringSupplier implements ObjectSupplier<int[]> {

  public static final int MAX_RANDOM_INTEGER = 9;
  private static final Random random = new Random();
  private final int digitsLength;

  public FixedLengthRandomNumbersStringSupplier(int digitsLength) {

    if (digitsLength < 0) {
      throw new NegativeIntegerNotSupportedException();
    }
    this.digitsLength = digitsLength;
  }


  @Override
  public int[] supply() {

    final var randomDigitsArray = new int[digitsLength];

    for (int index = 0; index < digitsLength; index++) {
      randomDigitsArray[index] = random.nextInt(MAX_RANDOM_INTEGER);
    }

    return randomDigitsArray;
  }
}