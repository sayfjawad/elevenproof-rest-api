package nl.multicode.elevenproof.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BsnElevenProofTest {

  private BsnElevenProof bsnElevenProof;

  @BeforeEach
  public void setup() {

    bsnElevenProof = new BsnElevenProof();
  }

  @Test
  void isGeldigBSN_True() {

    assertThat(bsnElevenProof.isElevenProof("273279865")).isTrue();
    assertThat(bsnElevenProof.isElevenProof("942884000")).isTrue();
  }

  @Test
  void isGeldigBSN_False() {

    assertThat(bsnElevenProof.isElevenProof("111111111")).isFalse();
    assertThat(bsnElevenProof.isElevenProof("222222222")).isFalse();
    assertThat(bsnElevenProof.isElevenProof("11111111")).isFalse();
    assertThat(bsnElevenProof.isElevenProof("X11111111")).isFalse();
  }
}
