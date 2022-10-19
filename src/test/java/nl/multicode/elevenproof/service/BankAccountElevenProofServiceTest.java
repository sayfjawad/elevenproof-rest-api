package nl.multicode.elevenproof.service;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountElevenProofServiceTest {

    @Test
    void generate() {
        BankAccountElevenProofService service = new BankAccountElevenProofService();
        Optional<String> generated = service.generate();
        assertThat(generated).isNotEmpty();
        assertThat(generated.get().length()).isEqualTo(10);
        assertThat(service.isValid(generated.get())).isTrue();
    }

    @Test
    void isValid() {
        assertThat(new BankAccountElevenProofService().isValid("0609567128")).isTrue();
        assertThat(new BankAccountElevenProofService().isValid("0609567127")).isFalse();
    }
}