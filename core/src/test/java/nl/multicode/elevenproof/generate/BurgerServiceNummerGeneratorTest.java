package nl.multicode.elevenproof.generate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import nl.multicode.elevenproof.model.ProofType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BurgerServiceNummerGeneratorTest {

  @Test
  @DisplayName("Given the generator is implemented correctly"
      + "When the generate() method is called withe the correct ProofType"
      + "Then a valid eleven-proof BSN number is generated and returned")
  void generate() {

    Optional<int[]> generatedResult = new BurgerServiceNummerGenerator().generate(ProofType.BSN);
    assertThat(generatedResult).isNotEmpty();
    assertThat(generatedResult.get()).hasSize(9);
  }

  @Test
  @DisplayName("Given the generator is implemented correctly"
      + "When the generate() method is called with an invalid ProofType"
      + "Then an empty Optional is returned")
  void generate_empty() {

    assertThat(new BurgerServiceNummerGenerator().generate(ProofType.BANK_ACCOUNT)).isEmpty();
  }
}