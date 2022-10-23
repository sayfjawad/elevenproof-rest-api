package nl.multicode.elevenproof.validation.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.model.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InputArgumentsValidationRuleTest {


  @Mock
  private MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;
  @Mock
  private UnknownArgumentsRule unknownArgumentsRule;
  @Mock
  private MissingValidateArgumentsRule missingValidateArgumentsRule;

  @InjectMocks
  private InputArgumentsValidationRule rule;

  @ParameterizedTest
  @CsvSource(value = {"validate", "generate"})
  @DisplayName("Given all method is called" +
      "When the Command argument is supported" +
      "Then applicable validation rules are used" +
      "And the result is TRUE")
  void isValid_true(String command) {

    String[] args = {command, "bsn", "some bsn"};
    when(minimalNumberOfArgumentsRule.isValid(args)).thenReturn(Boolean.TRUE);
    when(unknownArgumentsRule.isValid(args)).thenReturn(Boolean.TRUE);
    if (Command.VALIDATE.equals(Command.fromValue(command))) {
      when(missingValidateArgumentsRule.isValid(args)).thenReturn(Boolean.TRUE);
    }

    assertThat(rule.isValid(args)).isTrue();
    verify(unknownArgumentsRule).isValid(args);
    if (Command.VALIDATE.equals(Command.fromValue(command))) {
      verify(missingValidateArgumentsRule).isValid(args);
    }
  }

  @ParameterizedTest
  @CsvSource(value = {"unknown", "nonsense"})
  @DisplayName("Given all method is called" +
      "When the Command argument is not supported" +
      "Then NO validation rules are used" +
      "And the result is FALSE")
  void isValid_false(String command) {

    String[] args = {command, "bsn", "some bsn"};

    if (Command.VALIDATE.equals(Command.fromValue(command))) {
      when(missingValidateArgumentsRule.isValid(args)).thenReturn(Boolean.TRUE);
    }

    assertThat(rule.isValid(args)).isFalse();
    verify(minimalNumberOfArgumentsRule).isValid(args);
    verifyNoInteractions(unknownArgumentsRule);
    verifyNoInteractions(missingValidateArgumentsRule);
  }
}