package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountNumberDtoElevenProofTest {

    BankAccountNumberElevenProof elevenProof;

    @BeforeEach
    public void setup() {

        elevenProof = new BankAccountNumberElevenProof();
    }

    @Test
    void isElevenProof() {

        assertThat(elevenProof.test(new int[]{0, 4, 8, 4, 8, 4, 8, 4, 8, 8})).isTrue();
    }

    @Test
    void isElevenProof_TooLong() {

        assertThat(elevenProof.test(new int[]{0, 4, 8, 4, 8, 4, 8, 4, 8, 8, 9})).isFalse();
    }

    @Test
    void isElevenProof_Null() {

        assertThat(elevenProof.test(null)).isFalse();
    }

    @Test
    void isNotElevenProof() {

        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 8, 8, 8, 8, 8})).isFalse();
        assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 8, 8, 8, 8, 8, 9})).isFalse();
    }
}