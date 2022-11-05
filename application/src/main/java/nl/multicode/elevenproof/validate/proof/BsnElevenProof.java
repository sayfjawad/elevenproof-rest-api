package nl.multicode.elevenproof.validate.proof;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.validate.ElevenProof;

@RequiredArgsConstructor
public class BsnElevenProof implements ElevenProof {

  private static final int[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};

  @Override
  public boolean isElevenProof(int[] bsn) {

    return bsn != null && bsn.length == BSN_ONDNR_MULTIPLIERS.length && isElevenProof(bsn, BSN_ONDNR_MULTIPLIERS);
  }
}
