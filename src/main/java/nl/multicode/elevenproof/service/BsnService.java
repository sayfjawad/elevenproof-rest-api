package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.supplier.ObjectSupplier;
import nl.multicode.elevenproof.service.supplier.Random9DigitsStringSupplier;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BsnService implements ElevenProofService {

    private final ObjectSupplier<String> random9DigitsSupplier;
    private final ElevenProof elevenProof;

    public BsnService() {
        this.random9DigitsSupplier = new Random9DigitsStringSupplier();
        this.elevenProof = new BsnElevenProof();
    }

    public BsnService(ElevenProof elevenProof, ObjectSupplier<String> random9DigitsSupplier) {
        this.random9DigitsSupplier = random9DigitsSupplier;
        this.elevenProof = elevenProof;
    }

    public Optional<String> generate() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
                random9DigitsSupplier::supply);

        return infiniteStreamOfRandom9DigitNumbers
                .filter(elevenProof::isElevenProof)
                .findFirst();
    }

    public boolean isValid(String bsn) {
        return elevenProof.isElevenProof(bsn);
    }
}
