package nl.multicode.elevenproof.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.multicode.elevenproof.validation.ElevenProofConstraint;

@Getter
@AllArgsConstructor
public class ElevenProofNumber {

  @ElevenProofConstraint()
  private final String nummer;

}