package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.supplier.ObjectSupplier;
import nl.multicode.elevenproof.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.elevenproof.validation.BankAccountElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BankAccountElevenProofService implements ElevenProofService {

    public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;
    private final ObjectSupplier<String> randomDigitsSupplier;
    private final ElevenProof elevenProof;

    public BankAccountElevenProofService() {
        this(new RandomDigitsStringSupplier(BANK_ACCOUNT_DIGITS_LENGTH), new BankAccountElevenProof());
    }

    public Optional<String> generate() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
                randomDigitsSupplier::supply);
        return infiniteStreamOfRandom9DigitNumbers
                .filter(elevenProof::isElevenProof)
                .findFirst();
    }

    public boolean isValid(String bankAccount) {
        return elevenProof.isElevenProof(bankAccount);
    }
}
