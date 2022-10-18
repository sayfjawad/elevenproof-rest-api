package nl.multicode.bsn.validation;

public class BsnElfproef implements ElfProef {

    private static final int[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};
    private static final String VALID_BSN_REGEX = "[0-9]{9}";

    @Override
    public boolean isElfProef(String bsn) {
        if (bsn.matches(VALID_BSN_REGEX)) {
            return isElfProef(bsn, BSN_ONDNR_MULTIPLIERS);
        }
        return false;
    }
}
