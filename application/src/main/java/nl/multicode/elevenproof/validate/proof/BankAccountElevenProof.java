package nl.multicode.elevenproof.validate.proof;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.validate.ElevenProof;

@RequiredArgsConstructor
public class BankAccountElevenProof implements ElevenProof {

  private static final int[] BANK_ACCOUNT_MULTIPLIERS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

  @Override
  public boolean isElevenProof(int[] acountNr) {

    return acountNr != null && acountNr.length == BANK_ACCOUNT_MULTIPLIERS.length && isElevenProof(acountNr, BANK_ACCOUNT_MULTIPLIERS);
  }
}
