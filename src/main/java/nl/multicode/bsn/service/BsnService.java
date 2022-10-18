package nl.multicode.bsn.service;

import java.util.Optional;
import java.util.stream.Stream;
import nl.multicode.bsn.service.supplier.ObjectSupplier;
import nl.multicode.bsn.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.bsn.validation.BsnElevenProof;
import nl.multicode.bsn.validation.ElevenProof;

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

    public Boolean isValidBsn(String bsn) {
        return elevenProof.isElevenProof(bsn);
    }
}
