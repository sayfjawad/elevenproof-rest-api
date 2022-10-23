package nl.multicode.elevenproof.validation.input;

import static org.assertj.core.api.Assertions.assertThat;

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
      "Then the outcome should be FALSE")
  void isValid_false() {

    assertThat(rule.isValid(new String[]{"generate"})).isFalse();
    assertThat(rule.isValid(new String[]{})).isFalse();
  }

  @Test
  @DisplayName("Given the number of arguments is equal to the minimum of 2" +
      "When the method is called" +
      "Then the outcome should be TRUE")
  void isValid_true() {

    assertThat(rule.isValid(new String[]{"generate", "bsn"})).isTrue();
    assertThat(rule.isValid(new String[]{"validate", "bsn", "some bsn nr"})).isTrue();
  }
}