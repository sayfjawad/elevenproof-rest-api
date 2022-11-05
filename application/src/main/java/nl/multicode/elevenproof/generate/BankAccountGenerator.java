package nl.multicode.elevenproof.generate;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.generate.supply.FixedLengthRandomNumbersStringSupplier;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.proof.BankAccountElevenProof;

@RequiredArgsConstructor
public class BankAccountGenerator implements Generator {

  public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;
  private final ObjectSupplier<int[]> randomDigitsSupplier;
  private final BankAccountElevenProof elevenProof;

  public BankAccountGenerator() {

    this(new FixedLengthRandomNumbersStringSupplier(BANK_ACCOUNT_DIGITS_LENGTH), new BankAccountElevenProof());
  }

  @Override
  public Optional<int[]> generate(ProofType proofType) {

    if (ProofType.BANK_ACCOUNT.equals(proofType)) {
      return Stream.generate(randomDigitsSupplier::supply)
          .filter(elevenProof::isElevenProof)
          .findFirst()
          ;
    }
    return Optional.empty();
  }
}
