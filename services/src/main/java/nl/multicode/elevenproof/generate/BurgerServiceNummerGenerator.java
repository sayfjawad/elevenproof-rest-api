package nl.multicode.elevenproof.generate;

import java.util.stream.Stream;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;

public class BurgerServiceNummerGenerator implements Generator {

  public static final int BSN_DIGITS_LENGTH = 9;
  private final ObjectSupplier<int[]> randomDigitsSupplier;
  private final IntArrayToString intArrayToString;
  private final ElevenProof numberElevenProof;

  public BurgerServiceNummerGenerator(final ObjectSupplier<int[]> randomDigitsSupplier,
      final IntArrayToString intArrayToString,
      final ElevenProof elevenProof) {

    this.randomDigitsSupplier = randomDigitsSupplier;
    this.intArrayToString = intArrayToString;
    this.numberElevenProof = elevenProof;
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
