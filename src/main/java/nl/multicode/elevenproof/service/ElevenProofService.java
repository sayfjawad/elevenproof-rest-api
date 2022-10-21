package nl.multicode.elevenproof.service;

import java.util.Optional;

public interface ElevenProofService {

    Optional<String> generate();

    Boolean isValid(String number);
}
