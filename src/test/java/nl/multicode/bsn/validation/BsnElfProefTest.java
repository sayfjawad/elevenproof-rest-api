package nl.multicode.bsn.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BsnElfProefTest {

    private BsnElfproef bsnElfproef;

    @Before
    public void setup() {
        bsnElfproef = new BsnElfproef();
    }

    @Test
    public void isGeldigBSN_True() {
        assertTrue(bsnElfproef.isElfproef("273279865"));
        assertTrue(bsnElfproef.isElfproef("942884000"));
    }

    @Test
    public void isGeldigBSN_False() {
        assertFalse(bsnElfproef.isElfproef("111111111"));
        assertFalse(bsnElfproef.isElfproef("222222222"));
        assertFalse(bsnElfproef.isElfproef("11111111"));
        assertFalse(bsnElfproef.isElfproef("X11111111"));
    }
}
