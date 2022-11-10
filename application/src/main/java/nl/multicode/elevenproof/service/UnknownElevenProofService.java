package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnknownElevenProofService implements ElevenProofService {

    public Optional<int[]> generate() {

        return Optional.empty();
    }

    public boolean isValid(int[] number) {

        return false;
    }
}
