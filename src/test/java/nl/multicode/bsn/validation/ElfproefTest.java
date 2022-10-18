package nl.multicode.bsn.validation;


import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.jupiter.api.Test;

public class ElfproefTest {

    private ElfProef elfProef;

    @Test
    public void multipliers() {
        elfProef = createElfproefImpl(new int[]{1, 2});
        assertTrue(elfProef.isElfProef("15"));
        assertFalse(elfProef.isElfProef("16"));
    }

    private ElfProef createElfproefImpl(int[] multipliers) {
        return new ElfProef() {
            @Override
            public boolean isElfProef(String nummer) {
                return isElfProef(nummer, multipliers);
            }
        };
    }
}
