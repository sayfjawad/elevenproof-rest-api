package nl.multicode.elevenproof.validate;

public class BankAccountElevenProof implements ElevenProof {

  private static final int[] BANK_ACCOUNT_MULTIPLIERS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
  private static final String VALID_BANK_ACCOUNT_REGEX = "\\d{10}";

  @Override
  public boolean isElevenProof(String acountNr) {

    return acountNr.matches(VALID_BANK_ACCOUNT_REGEX) && isElevenProof(acountNr, BANK_ACCOUNT_MULTIPLIERS);
  }
}
