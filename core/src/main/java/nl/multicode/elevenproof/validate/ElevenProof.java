package nl.multicode.elevenproof.validate;

import java.util.concurrent.atomic.AtomicInteger;

public interface ElevenProof {

  boolean isElevenProof(String nummer);

  default boolean isElevenProof(String bsn, int[] digitPositionMultipliers) {

    final var digits = getDigits(bsn);
    final var zeroOutcome = 0;
    final var sum = new AtomicInteger(0);
    for (int i = 0; i < digitPositionMultipliers.length; i++) {
      sum.addAndGet(digits[i] * digitPositionMultipliers[i]);
    }
    return sum.get() % 11 == zeroOutcome;
  }

  default int[] getDigits(String number) {

    final var digitArray = new int[number.length()];
    final var numberCharArray = number.toCharArray();
    for (int i = 0; i < number.length(); i++) {
      digitArray[i] = Character.getNumericValue(numberCharArray[i]);
    }

    return digitArray;
  }
}
