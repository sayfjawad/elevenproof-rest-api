package nl.multicode.elevenproof.generate;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.generate.supply.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.ElevenProof;
import nl.multicode.elevenproof.validate.proof.BsnElevenProof;

@RequiredArgsConstructor
public class BurgerServiceNummerGenerator implements Generator {

  private static final int BSN_DIGITS_LENGTH = 9;
  private final ObjectSupplier<int[]> randomDigitsSupplier;
  private final ElevenProof elevenProof;

  public BurgerServiceNummerGenerator() {

    this(new FixedLengthStringRandomNumbersSupplier(BSN_DIGITS_LENGTH), new BsnElevenProof());
  }

  @Override
  public Optional<int[]> generate(ProofType proofType) {

    if (ProofType.BSN.equals(proofType)) {
      return Stream.generate(randomDigitsSupplier::supply)
          .filter(elevenProof::isElevenProof)
          .findFirst();
    }
    return Optional.empty();
  }
}
