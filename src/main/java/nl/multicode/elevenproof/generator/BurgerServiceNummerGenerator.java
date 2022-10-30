package nl.multicode.elevenproof.generator;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generator.supplier.ObjectSupplier;
import nl.multicode.elevenproof.generator.supplier.FixedLengthRandomNumbersStringSupplier;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

@RequiredArgsConstructor
public class BurgerServiceNummerGenerator implements Generator {

  private static final int BSN_DIGITS_LENGTH = 9;
  private final ObjectSupplier<String> randomDigitsSupplier;
  private final ElevenProof elevenProof;

  public BurgerServiceNummerGenerator() {

    this(new FixedLengthRandomNumbersStringSupplier(BSN_DIGITS_LENGTH), new BsnElevenProof());
  }

  @Override
  public Optional<String> generate(ProofType proofType) {

    if (ProofType.BSN.equals(proofType)) {
      return Stream.generate(randomDigitsSupplier::supply)
          .filter(elevenProof::isElevenProof)
          .findFirst();
    }
    return Optional.empty();
  }
}
