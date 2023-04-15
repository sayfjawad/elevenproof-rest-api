package nl.multicode.elevenproof.generate;

import java.util.stream.Stream;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;

public class BankAccountNumberGenerator implements Generator {

  public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;
  private final ObjectSupplier<int[]> randomDigitsSupplier;
  private final IntArrayToString intArrayToString;
  private final ElevenProof numberElevenProof;

  public BankAccountNumberGenerator(final ObjectSupplier<int[]> randomDigitsSupplier,
      final IntArrayToString intArrayToString,
      final ElevenProof numberElevenProof) {

    this.randomDigitsSupplier = randomDigitsSupplier;
    this.intArrayToString = intArrayToString;
    this.numberElevenProof = numberElevenProof;
  }

  @Override
  public String generate() {

    return Stream.generate(randomDigitsSupplier::supply)
        .filter(numberElevenProof::test)
        .map(intArrayToString)
        .findFirst()
        .orElse(null);
  }
}
