package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ElevenProofTest {

    private ElevenProof elevenProof;

    @BeforeEach
    public void before() {

        elevenProof = nummer -> false;
    }

    @Test
    @DisplayName("Given the number and multiplier digits are of unequal length"
            + "When the isElevenProof() method is called"
            + "Then the outcome is false")
    void isElevenProof_invalid_input_arrays_length_unequal() {

        int[] number = {2, 5};
        int[] digitPositionMultipliers = {9, 8, 7};
        assertThat(elevenProof.test(number, digitPositionMultipliers)).isFalse();
    }

    @Test
    @DisplayName("Given the number and multiplier digits are both null"
            + "When the isElevenProof() method is called"
            + "Then the outcome is false")
    void isElevenProof_invalid_input_null_arrays() {

        assertThat(elevenProof.test(null, null)).isFalse();
        assertThat(elevenProof.test(new int[]{1}, new int[]{10})).isFalse();
    }

    @Test
    @DisplayName("Given the number is null and multiplier isn't"
            + "When the isElevenProof() method is called"
            + "Then the outcome is false")
    void isElevenProof_invalid_input_number_array_null() {

        int[] digitPositionMultipliers = {9, 8, 7, 6, 5, 4, 3, 2, -1};
        assertThat(elevenProof.test(null, digitPositionMultipliers)).isFalse();
    }

    @Test
    @DisplayName("Given the multiplier digits are null"
            + "When the isElevenProof() method is called"
            + "Then the outcome is false")
    void isElevenProof_invalid_input_digits_multipliers_array_null() {

        int[] number = {2, 5};
        assertThat(elevenProof.test(number, null)).isFalse();
    }

    @Test
    @DisplayName("Given the number and multiplier digits are of equal length"
            + "And the number is elevenProof"
            + "When the isElevenProof() method is called"
            + "Then the outcome is true")
    void isElevenProof_is_elevenProof() {

        int[] number = {2, 5, 3, 0, 4, 7, 1, 4, 6};
        int[] digitPositionMultipliers = {9, 8, 7, 6, 5, 4, 3, 2, -1};
        assertThat(elevenProof.test(number, digitPositionMultipliers)).isTrue();
    }

    @Test
    @DisplayName("Given the number and multiplier digits are of equal length"
            + "And the number is NOT elevenProof"
            + "When the isElevenProof() method is called"
            + "Then the outcome is false")
    void isElevenProof_is_not_elevenProof() {

        int[] number = {2, 5, 3, 0, 4, 7, 1, 4, 7};
        int[] digitPositionMultipliers = {9, 8, 7, 6, 5, 4, 3, 2, -1};
        assertThat(elevenProof.test(number, digitPositionMultipliers)).isFalse();
    }
}