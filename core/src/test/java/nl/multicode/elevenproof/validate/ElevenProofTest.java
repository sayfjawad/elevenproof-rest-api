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
  void isElevenProof() {

    assertThat(elevenProof.isElevenProof(new int[]{1}, new int[]{11})).isTrue();
    assertThat(elevenProof.isElevenProof(new int[]{1}, new int[]{12})).isFalse();
  }
}