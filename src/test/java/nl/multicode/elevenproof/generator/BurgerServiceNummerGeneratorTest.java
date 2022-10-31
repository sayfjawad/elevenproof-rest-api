package nl.multicode.elevenproof.generator;

import static org.assertj.core.api.Assertions.assertThat;

import nl.multicode.elevenproof.model.ProofType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BurgerServiceNummerGeneratorTest {

    @Test
    @DisplayName("Given the generator is implemented correctly"
        + "When the generate() method is called withe the correct ProofType"
        + "Then a valid eleven-proof BSN number is generated and returned")
    void generate() {

        assertThat(new BurgerServiceNummerGenerator().generate(ProofType.BSN)).isNotEmpty();
        assertThat(new BurgerServiceNummerGenerator().generate(ProofType.BSN).get()).hasSize(9);
    }

    @Test
    @DisplayName("Given the generator is implemented correctly"
        + "When the generate() method is called with an invalid ProofType"
        + "Then an empty Optional is returned")
    void generate_empty() {

        assertThat(new BurgerServiceNummerGenerator().generate(ProofType.BANK_ACCOUNT)).isEmpty();
    }
}