package nl.multicode.bsn.validation;

import java.util.concurrent.atomic.AtomicInteger;

public interface ElevenProof {

    boolean isElevenProof(String nummer);

    default boolean isElevenProof(String bsn, int[] digitPositionMultipliers) {
        int[] digits = getDigitsFromString(bsn);
        final int zeroOutcome = 0;
        AtomicInteger sum = new AtomicInteger(0);
        for (int i = 0; i < digitPositionMultipliers.length; i++) {
            sum.addAndGet(digits[i] * digitPositionMultipliers[i]);
        }
        return sum.get() % 11 == zeroOutcome;
    }

    default int[] getDigitsFromString(String number) {
        int[] digitArray = new int[number.length()];
        char[] numberCharArray = number.toCharArray();
        for (int i = 0; i < number.length(); i++) {
            digitArray[i] = Character.getNumericValue(numberCharArray[i]);
        }

        return digitArray;
    }
}
