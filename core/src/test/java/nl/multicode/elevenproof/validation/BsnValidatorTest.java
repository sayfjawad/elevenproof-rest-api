package nl.multicode.elevenproof.validation;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.Validation;
import javax.validation.Validator;
import nl.multicode.elevenproof.model.ElevenProofNumber;
import org.junit.jupiter.api.Test;

public class BsnValidatorTest {

  private static final String VALID_BSN = "142279675";
  private static final String[] INVALID_BSN = {"123456789", "A23456789", "1234567", "%$#@", null};
  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void test_valid() {

    assertThat(validator.validate(new ElevenProofNumber(VALID_BSN))).isEmpty();
  }

  @Test
  public void test_invalid() {

    for (String invalideBSN : INVALID_BSN) {
      assertThat(validator.validate(new ElevenProofNumber(invalideBSN))).isNotEmpty();
    }
  }

}