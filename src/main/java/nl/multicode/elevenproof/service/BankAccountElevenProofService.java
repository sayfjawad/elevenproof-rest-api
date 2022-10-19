package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.supplier.ObjectSupplier;
import nl.multicode.elevenproof.service.supplier.Random10DigitsStringSupplier;
import nl.multicode.elevenproof.validation.BankAccountElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BankAccountElevenProofService implements ElevenProofService {

    private final ObjectSupplier<String> random10DigitsSupplier;
    private final ElevenProof elevenProof;

    public BankAccountElevenProofService() {
        this(new BankAccountElevenProof(), new Random10DigitsStringSupplier());
    }

    public BankAccountElevenProofService(ElevenProof elevenProof, ObjectSupplier<String> random9DigitsSupplier) {
        this.random10DigitsSupplier = random9DigitsSupplier;
        this.elevenProof = elevenProof;
    }

    public Optional<String> generate() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
                random10DigitsSupplier::supply);

        return infiniteStreamOfRandom9DigitNumbers
                .filter(elevenProof::isElevenProof)
                .findFirst();
    }

    public boolean isValid(String bankAccount) {
        return elevenProof.isElevenProof(bankAccount);
    }
}
