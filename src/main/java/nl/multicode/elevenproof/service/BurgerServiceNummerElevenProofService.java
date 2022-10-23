package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generator.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.generator.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

@RequiredArgsConstructor
public class BurgerServiceNummerElevenProofService implements ElevenProofService {

  private final Generator elevenproofGenerator;
  private final ElevenProof elevenProof;

  public BurgerServiceNummerElevenProofService() {

    this(new BurgerServiceNummerGenerator(), new BsnElevenProof());
  }

  public Optional<String> generate() {

    return elevenproofGenerator.generate(ProofType.BSN);
  }

  public boolean isValid(String bsn) {

    return elevenProof.isElevenProof(bsn);
  }
}
