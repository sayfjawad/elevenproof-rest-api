package nl.multicode.elevenproof.validate;

import java.util.concurrent.atomic.AtomicInteger;

public interface ElevenProof {

  boolean isElevenProof(int[] nummer);

  default boolean isElevenProof(int[] number, int[] digitPositionMultipliers) {

    final var zeroOutcome = 0;
    final var sum = new AtomicInteger(0);
    for (int i = 0; i < digitPositionMultipliers.length; i++) {
      sum.addAndGet(number[i] * digitPositionMultipliers[i]);
    }
    return sum.get() % 11 == zeroOutcome;
  }
}
