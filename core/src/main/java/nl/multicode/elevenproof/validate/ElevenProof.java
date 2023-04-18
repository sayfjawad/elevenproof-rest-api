package nl.multicode.elevenproof.validate;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public interface ElevenProof extends Predicate<int[]> {

    boolean test(int[] nummer);

    default boolean test(int[] number, int[] digitPositionMultipliers) {

        if (isValidInput(number, digitPositionMultipliers)) {
            return isDividableByEleven(getMultiplicationSum(number, digitPositionMultipliers));
        }
        return false;
    }

    private boolean isValidInput(int[] number, int[] digitPositionMultipliers) {

        return number != null && digitPositionMultipliers != null
                && number.length == digitPositionMultipliers.length;
    }

    private int getMultiplicationSum(int[] number, int[] digitPositionMultipliers) {

        return IntStream.range(0, number.length)
                .map(index -> number[index] * digitPositionMultipliers[index])
                .sum();
    }

    private boolean isDividableByEleven(int sum) {

        return sum % 11 == 0;
    }
}
