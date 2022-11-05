package nl.multicode.elevenproof.validate.input;

import static org.assertj.core.api.Assertions.assertThat;

import nl.multicode.elevenproof.validate.rule.MinimalNumberOfArgumentsRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinimalNumberOfArgumentsRuleTest {

  private MinimalNumberOfArgumentsRule rule;

  @BeforeEach
  public void before() {

    rule = new MinimalNumberOfArgumentsRule();
  }

  @Test
  @DisplayName("Given the number of arguments is below the minimum of 2" +
      "When the method is called" +
      "Then Optional<Error> is returned")
  void isValid_return_optional_error() {

    assertThat(rule.isValid(new String[]{"generate"})).isPresent();
    assertThat(rule.isValid(new String[]{})).isPresent();
    assertThat(rule.isValid(new String[]{}).get().getMessage()).isEqualTo("Number of supplied arguments is insufficient.");
  }

  @Test
  @DisplayName("Given any situation" +
      "When the method is called" +
      "Then is always returns true")
  void isApplicable_always_return_true() {

    assertThat(rule.isApplicable(new String[]{"generate"})).isTrue();
  }

  @Test
  @DisplayName("Given the number of arguments is equal to the minimum of 2" +
      "When the method is called" +
      "Then Optional.empty() is returned")
  void isValid_true() {

    assertThat(rule.isValid(new String[]{"generate", "bsn"})).isEmpty();
    assertThat(rule.isValid(new String[]{"validate", "bsn", "some bsn nr"})).isEmpty();
  }
}