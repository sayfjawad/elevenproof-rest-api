package nl.multicode.elevenproof.generator;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generator.supplier.ObjectSupplier;
import nl.multicode.elevenproof.generator.supplier.RandomFixedLengthDigitsSupplier;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.BankAccountElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BankAccountGenerator implements Generator {

    public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;
    private final ObjectSupplier<String> randomDigitsSupplier;
    private final ElevenProof elevenProof;

    public BankAccountGenerator() {
        this(new RandomFixedLengthDigitsSupplier(BANK_ACCOUNT_DIGITS_LENGTH), new BankAccountElevenProof());
    }

    @Override
    public Optional<String> generate(ProofType proofType) {
        if (ProofType.BANK_ACCOUNT.equals(proofType)) {
            return Stream.generate(randomDigitsSupplier::supply)
                    .filter(elevenProof::isElevenProof)
                    .findFirst();
        }
        return Optional.empty();
    }
}
