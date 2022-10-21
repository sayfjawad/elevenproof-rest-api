package nl.multicode.elevenproof.service;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class UnknownElevenProofService implements ElevenProofService {

    public Optional<String> generate() {
        return Optional.empty();
    }

    public boolean isValid(String number) {
        return false;
    }
}
