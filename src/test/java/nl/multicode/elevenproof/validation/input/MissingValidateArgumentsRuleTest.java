package nl.multicode.elevenproof.validation.input;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

class MissingValidateArgumentsRuleTest {

    @Mock
    private MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;

    MissingValidateArgumentsRule rule;

    @BeforeEach
    public void before() {

        rule = new MissingValidateArgumentsRule(minimalNumberOfArgumentsRule);
    }

/*  @Test
  @DisplayName("Given the command is 'validate'" +
      "And args contains 3 arguments" +
      "Then the outcome should be TRUE")
  void isValid_true() {

    assertThat(rule.isValid(new String[]{"validate", "bsn", "some bsn"})).isTrue();
  }

  @Test
  @DisplayName("Given the command is 'validate'" +
      "And args contains more or less than 3 arguments" +
      "Then the outcome should be TRUE")
  void isValid_false_invalid_nr_of_arguments() {

    assertThat(rule.isValid(new String[]{"validate", "bsn", "number", "nonsense"})).isFalse();
    assertThat(rule.isValid(new String[]{"validate", "bsn"})).isFalse();
    assertThat(rule.isValid(new String[]{"validate"})).isFalse();
  }

  @Test
  @DisplayName("Given the command is not 'validate'" +
      "And args contains more or less than 3 arguments" +
      "Then the outcome should be TRUE")
  void isValid_false_wrong_command() {

    assertThat(rule.isValid(new String[]{"generate", "bsn", "number"})).isFalse();
    assertThat(rule.isValid(new String[]{"unknown", "bsn", "number"})).isFalse();
  }*/
}