package nl.multicode.elevenproof.generator;

import nl.multicode.elevenproof.model.ProofType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountGeneratorTest {

    @Test
    void generate() {
        assertThat(new BankAccountGenerator().generate(ProofType.BANK_ACCOUNT)).isNotEmpty();
    }

    @Test
    void generate_empty() {
        assertThat(new BankAccountGenerator().generate(ProofType.BSN)).isEmpty();
    }
}