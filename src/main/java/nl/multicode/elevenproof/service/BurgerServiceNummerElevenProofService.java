package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.supplier.ObjectSupplier;
import nl.multicode.elevenproof.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BurgerServiceNummerElevenProofService implements ElevenProofService {

    private static final int BSN_DIGITS_LENGTH = 9;
    private final ObjectSupplier<String> randomDigitsSupplier;
    private final ElevenProof elevenProof;

    public BurgerServiceNummerElevenProofService() {
        this(new RandomDigitsStringSupplier(BSN_DIGITS_LENGTH), new BsnElevenProof());
    }

    public Optional<String> generate() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
                randomDigitsSupplier::supply);

        return infiniteStreamOfRandom9DigitNumbers
                .filter(elevenProof::isElevenProof)
                .findFirst();
    }

    public boolean isValid(String bsn) {
        return elevenProof.isElevenProof(bsn);
    }
}
