package nl.multicode.bsn.validation;

public class BankaccountElevenProof implements ElevenProof {

    private static final int[] BSN_ONDNR_MULTIPLIERS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private static final String VALID_BSN_REGEX = "[0-9]{10}";

    @Override
    public boolean isElevenProof(String bsn) {
        if (bsn.matches(VALID_BSN_REGEX)) {
            return isElevenProof(bsn, BSN_ONDNR_MULTIPLIERS);
        }
        return false;
    }
}
