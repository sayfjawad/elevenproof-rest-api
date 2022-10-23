package nl.multicode.elevenproof.validation.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnknownArgumentsRuleTest {

  UnknownArgumentsRule rule;

  @BeforeEach
  public void before() {

    rule = new UnknownArgumentsRule();
  }

  @Test
  @DisplayName("Given the one or both of the first two arguments is UNKNOWN"
      + "When then method is called" +
      "Then the outcome should be FALSE")
  void isValid_true_valid_arguments() {

    assertThat(rule.isValid(new String[]{"validate", "unknown proofType", "number"})).isFalse();
    assertThat(rule.isValid(new String[]{"unknown command", "bank", "number"})).isFalse();
    assertThat(rule.isValid(new String[]{"nonsense", "bla blee blue"})).isFalse();
  }

  @Test
  @DisplayName("Given the one or both of the first two arguments is UNKNOWN"
      + "When then method is called" +
      "Then the outcome should be FALSE")
  void isValid_false_invalid_arguments() {

    assertThat(rule.isValid(new String[]{"validate", "bsn", "number"})).isTrue();
    assertThat(rule.isValid(new String[]{"generate", "bank", "number"})).isTrue();
  }
}