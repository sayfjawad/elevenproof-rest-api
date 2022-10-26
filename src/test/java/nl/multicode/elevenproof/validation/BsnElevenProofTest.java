package nl.multicode.elevenproof.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BsnElevenProofTest {

  private BsnElevenProof bsnElevenProof;

  @BeforeEach
  public void setup() {

    bsnElevenProof = new BsnElevenProof();
  }

  @ParameterizedTest
  @CsvSource({"253047146", "015715747", "581532053", "715078483"})
  @DisplayName("Given that the isElevenProof is doing the BSN proof correctly "
      + "When the method is called with a valid BSN number"
      + "Then TRUE is returned")
  void isElevenProof_True(String number) {

    assertThat(bsnElevenProof.isElevenProof(number)).isTrue();
  }

  @ParameterizedTest
  @CsvSource({"111222330", "017717777", "221222023", "120978651"})
  @DisplayName("Given that the isElevenProof is doing the BSN proof correctly "
      + "When the method is called with an invalid BSN number"
      + "Then FALSE is returned")
  void isElevenProof_False(String number) {

    assertThat(bsnElevenProof.isElevenProof(number)).isFalse();
  }
}
