package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class BankAccountElevenProofServiceTest {

  @Test
  void generate() {

    BankAccountElevenProofService service = new BankAccountElevenProofService();
    Optional<String> generated = service.generate();
    assertThat(generated).isNotEmpty();
    assertThat(generated.get()).hasSize(10);
    assertThat(service.isValid(generated.get())).isTrue();
  }

  @Test
  void isValid() {

    assertThat(new BankAccountElevenProofService().isValid("0609567128")).isTrue();
    assertThat(new BankAccountElevenProofService().isValid("0609567127")).isFalse();
  }
}