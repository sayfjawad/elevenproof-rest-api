package nl.multicode.elevenproof.service;

import java.util.Optional;
import java.util.stream.Stream;
import nl.multicode.elevenproof.service.supplier.ObjectSupplier;
import nl.multicode.elevenproof.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

public class BsnService {

    private final ObjectSupplier<String> random9DigitsSupplier;
    private final ElevenProof elevenProof;

    public BsnService() {
        random9DigitsSupplier = new RandomDigitsStringSupplier();
        elevenProof = new BsnElevenProof();
    }

    public BsnService(ElevenProof elevenProof, ObjectSupplier<String> random9DigitsSupplier) {
        this.random9DigitsSupplier = random9DigitsSupplier;
        this.elevenProof = elevenProof;
    }

    public Optional<String> generateRandomBsnNummers() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
            random9DigitsSupplier::supply);

        return infiniteStreamOfRandom9DigitNumbers
            .filter(elevenProof::isElevenProof)
            .findFirst();
    }

    public boolean isValidBsn(String bsn) {
        return elevenProof.isElevenProof(bsn);
    }
}
