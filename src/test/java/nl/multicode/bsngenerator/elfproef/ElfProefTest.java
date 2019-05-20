package nl.multicode.bsngenerator.elfproef;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElfProefTest {

    ElfProef elfProef;

    @Before
    public void setup(){
        elfProef = new ElfProef();
    }

    @Test
    public void isGeldigBSN_String() {
        assertTrue(elfProef.isGeldigBSN("273279865"));
        assertFalse(elfProef.isGeldigBSN("111111111l"));
    }

    @Test
    public void isGeldigBSN_Long() {
        assertTrue(elfProef.isGeldigBSN(273279865l));
        assertFalse(elfProef.isGeldigBSN(111111111l));

    }
}
