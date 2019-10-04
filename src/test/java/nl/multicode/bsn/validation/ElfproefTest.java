package nl.multicode.bsn.validation;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ElfproefTest {
    private ElfProef elfProef;

    @Test
    public void multipliers() {
        elfProef = createElfproefImpl(new int[]{1, 2});
        assertTrue(elfProef.isElfproef("15"));
        assertFalse(elfProef.isElfproef("16"));
    }

    private ElfProef createElfproefImpl(int[] multipliers) {
        return new ElfProef() {
            @Override
            public boolean isElfproef(String nummer) {
                return isElfProef(nummer, multipliers);
            }
        };
    }
}
