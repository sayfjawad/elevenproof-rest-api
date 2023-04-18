package nl.multicode.elevenproof.generate.supplier;

import java.util.Random;
import nl.multicode.elevenproof.generate.supplier.exception.NegativeIntegerNotSupportedException;

public class FixedLengthStringRandomNumbersSupplier implements ObjectSupplier<int[]> {

    public static final int SINGLE_DIGIT_MAX_RANDOM_NUMBER = 9;

    private static final Random random = new Random();

    private final int digitsLength;

    public FixedLengthStringRandomNumbersSupplier(int digitsLength) {

        if (digitsLength < 0) {
            throw new NegativeIntegerNotSupportedException();
        }
        this.digitsLength = digitsLength;
    }


    @Override
    public int[] supply() {

        final var randomDigitsArray = new int[digitsLength];

        for (int index = 0; index < digitsLength; index++) {
            randomDigitsArray[index] = random.nextInt(SINGLE_DIGIT_MAX_RANDOM_NUMBER);
        }

        return randomDigitsArray;
    }
}