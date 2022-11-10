package nl.multicode.elevenproof.service;

import java.util.Optional;

public interface ElevenProofService {

    Optional<int[]> generate();

    boolean isValid(int[] number);
}
