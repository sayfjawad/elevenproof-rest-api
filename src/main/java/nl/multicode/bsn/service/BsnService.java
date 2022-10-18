package nl.multicode.bsn.service;

import java.util.Optional;
import java.util.stream.Stream;
import nl.multicode.bsn.service.supplier.ObjectSupplier;
import nl.multicode.bsn.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.bsn.validation.BsnElfproef;
import nl.multicode.bsn.validation.ElfProef;

public class BsnService {

    private final ObjectSupplier<String> random9DigitsSupplier;
    private final ElfProef elfProef;

    public BsnService() {
        random9DigitsSupplier = new RandomDigitsStringSupplier();
        elfProef = new BsnElfproef();
    }

    public BsnService(ElfProef elfProef, ObjectSupplier<String> random9DigitsSupplier) {
        this.random9DigitsSupplier = random9DigitsSupplier;
        this.elfProef = elfProef;
    }

    public Optional<String> generateRandomBsnNummers() {
        Stream<String> infiniteStreamOfRandom9DigitNumbers = Stream.generate(
            random9DigitsSupplier::supply);

        return infiniteStreamOfRandom9DigitNumbers
            .filter(elfProef::isElfProef)
            .findFirst();
    }

    public Boolean isValidBsn(String bsn) {
        return elfProef.isElfProef(bsn);
    }
}
