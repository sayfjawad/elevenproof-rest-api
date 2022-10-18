package nl.multicode.elevenproof.validation;

public class BsnElevenProof implements ElevenProof {

    private static final int[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};
    private static final String VALID_BSN_REGEX = "\\d{9}";

    @Override
    public boolean isElevenProof(String bsn) {
        return bsn.matches(VALID_BSN_REGEX) && isElevenProof(bsn, BSN_ONDNR_MULTIPLIERS);
    }
}
