package nl.multicode.elevenproof.validate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevenProofTest {

  private ElevenProof elevenProof;

  @BeforeEach
  public void before() {

    elevenProof = new ElevenProof() {
      @Override
      public boolean isElevenProof(int[] nummer) {

        return false;
      }
    };
  }

  @Test
  void isElevenProof_invalid_input_arrays_length_unequal() {

    assertThat(elevenProof.isElevenProof(new int[]{2, 5}, new int[]{9, 8, 7})).isFalse();
  }

  @Test
  void isElevenProof_invalid_input_null_arrays() {

    assertThat(elevenProof.isElevenProof(null, null)).isFalse();
  }

  @Test
  void isElevenProof_invalid_input_number_array_null() {

    assertThat(elevenProof.isElevenProof(null, new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1})).isFalse();
  }

  @Test
  void isElevenProof_invalid_input_digits_multipliers_array_null() {

    assertThat(elevenProof.isElevenProof(new int[]{2, 5}, null)).isFalse();
  }

  @Test
  void isElevenProof_is_elevenProof() {

    assertThat(elevenProof.isElevenProof(new int[]{2, 5, 3, 0, 4, 7, 1, 4, 6}, new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1})).isTrue();
  }

  @Test
  void isElevenProof_is_not_elevenProof() {

    assertThat(elevenProof.isElevenProof(new int[]{2, 5, 3, 0, 4, 7, 1, 4, 7}, new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1})).isFalse();
  }
}