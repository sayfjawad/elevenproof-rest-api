package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.ElevenProof;

@RequiredArgsConstructor
public class BankAccountElevenProofService implements ElevenProofService {

  private final Generator elevenproofGenerator;
  private final ElevenProof elevenProof;

  public Optional<int[]> generate() {

    return elevenproofGenerator.generate(ProofType.BANK_ACCOUNT);
  }

  public boolean isValid(int[] bankAccount) {

    return elevenProof.isElevenProof(bankAccount);
  }
}
