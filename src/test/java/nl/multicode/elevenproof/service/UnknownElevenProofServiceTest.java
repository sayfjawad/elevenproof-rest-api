package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnknownElevenProofServiceTest {

    private UnknownElevenProofService service;

    @BeforeEach
    public void before() {

        service = new UnknownElevenProofService();
    }

    @Test
    @DisplayName("Given the Service is working correctly"
        + "When the generate() method is called"
        + "Then an empty optional is returned ")
    void generate() {

        assertThat(service.generate()).isEmpty();
    }

    @Test
    @DisplayName("Given the Service is working correctly"
        + "When the isValid() method is called"
        + "Then a false boolean is returned ")
    void isValid() {

        assertThat(service.isValid("anyNumber")).isFalse();
    }
}