package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnknownElevenProofService implements ElevenProofService {

  public Optional<String> generate() {

    return Optional.empty();
  }

  public boolean isValid(String number) {

    return false;
  }
}
