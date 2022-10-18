package nl.multicode.bsn.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BsnElfProefTest {

    private BsnElfproef bsnElfproef;

    @BeforeEach
    public void setup() {
        bsnElfproef = new BsnElfproef();
    }

    @Test
    public void isGeldigBSN_True() {
        assertThat(bsnElfproef.isElfProef("273279865")).isTrue();
        assertThat(bsnElfproef.isElfProef("942884000")).isTrue();
    }

    @Test
    public void isGeldigBSN_False() {
        assertThat(bsnElfproef.isElfProef("111111111")).isFalse();
        assertThat(bsnElfproef.isElfProef("222222222")).isFalse();
        assertThat(bsnElfproef.isElfProef("11111111")).isFalse();
        assertThat(bsnElfproef.isElfProef("X11111111")).isFalse();
    }
}
