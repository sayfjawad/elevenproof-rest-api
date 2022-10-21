package nl.multicode.elevenproof.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UnknownElevenProofServiceTest {

    private UnknownElevenProofService service;

    @BeforeEach
    public void before(){
        service = new UnknownElevenProofService();
    }

    @Test
    void generate() {
        assertThat(service.generate()).isEmpty();
    }

    @Test
    void isValid() {
        assertThat(service.isValid("anyNumber")).isFalse();
    }
}