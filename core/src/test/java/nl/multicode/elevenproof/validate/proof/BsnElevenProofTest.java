package nl.multicode.elevenproof.validate.proof;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BsnElevenProofTest {

  BsnElevenProof elevenProof;

  @BeforeEach
  public void setup() {

    elevenProof = new BsnElevenProof();
  }

  @Test
  void isElevenProof() {

    assertThat(elevenProof.test(new int[]{4,3,0,3,5,3,8,0,7})).isTrue();
  }

  @Test
  void isNotElevenProof() {

    assertThat(elevenProof.test(new int[]{1, 2, 3, 4, 8, 8, 8, 8})).isFalse();
  }
}