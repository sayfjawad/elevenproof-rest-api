package nl.multicode.elevenproof.generate;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;

@RequiredArgsConstructor
public class BankAccountNumberGenerator implements Generator {

    public static final int BANK_ACCOUNT_DIGITS_LENGTH = 10;

    private final ObjectSupplier<int[]> randomDigitsSupplier;

    private final IntArrayToString intArrayToString;

    private final ElevenProof numberElevenProof;

    @Override
    public String generate() {

        return Stream.generate(randomDigitsSupplier::supply)
                .filter(numberElevenProof)
                .map(intArrayToString)
                .findFirst()
                .orElse(null);
    }
}
