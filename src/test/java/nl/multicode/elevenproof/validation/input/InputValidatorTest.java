package nl.multicode.elevenproof.validation.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InputValidatorTest {

  @Mock
  private MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;
  @Mock
  private MissingValidateArgumentsRule missingValidateArgumentsRule;
  @Mock
  private UnknownArgumentsRule unknownArgumentsRule;

  private InputValidator validator;

  @BeforeEach
  public void before() {

    validator = new InputValidator(List.of(
        missingValidateArgumentsRule,
        minimalNumberOfArgumentsRule,
        unknownArgumentsRule));
  }

  @Test
  @DisplayName("Given multiple validation rules are provided"
      + "And only one is applicable"
      + "When the applicable validation rule returns an error"
      + "Then other validators isValid method is not called")
  void validate() {

    final var args = new String[]{};
    final var error = new Error();
    when(minimalNumberOfArgumentsRule.isApplicable(args)).thenReturn(Boolean.TRUE);
    when(minimalNumberOfArgumentsRule.isValid(args)).thenReturn(Optional.of(error));
    when(missingValidateArgumentsRule.isApplicable(args)).thenReturn(Boolean.TRUE);
    when(missingValidateArgumentsRule.isValid(args)).thenReturn(Optional.empty());

    final var result = validator.validate(args);

    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(error);
    verify(minimalNumberOfArgumentsRule).isApplicable(args);
    verify(missingValidateArgumentsRule).isApplicable(args);
    verify(unknownArgumentsRule).isApplicable(args);

    verify(minimalNumberOfArgumentsRule).isValid(args);
    verify(missingValidateArgumentsRule).isValid(args);
    verify(unknownArgumentsRule, times(0)).isValid(args);
  }
}