package nl.multicode.elevenproof.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankaccountElevenProofTest {

  private BankAccountElevenProof bankaccountElevenProof;

  @BeforeEach
  public void setup() {

    bankaccountElevenProof = new BankAccountElevenProof();
  }

  @Test
  void isGeldigBSN_True() {

    assertThat(bankaccountElevenProof.isElevenProof("0248199544")).isTrue();
  }

  @Test
  void isGeldigBSN_False() {

    assertThat(bankaccountElevenProof.isElevenProof("0123456788")).isFalse();
    assertThat(bankaccountElevenProof.isElevenProof("2108123325")).isFalse();
    assertThat(bankaccountElevenProof.isElevenProof("111111111")).isFalse();
    assertThat(bankaccountElevenProof.isElevenProof("X11111111111")).isFalse();
  }
}