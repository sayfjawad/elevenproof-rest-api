package nl.multicode.elevenproof.validate;

public class BankAccountNumberElevenProof implements ElevenProof {

    private static final int[] BANK_ACCOUNT_MULTIPLIERS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    @Override
    public boolean test(int[] acountNr) {

        return acountNr != null &&
                acountNr.length == BANK_ACCOUNT_MULTIPLIERS.length &&
                test(acountNr, BANK_ACCOUNT_MULTIPLIERS);
    }
}
