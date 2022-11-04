package nl.multicode.elevenproof.validate.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnknownArgumentsRuleTest {

  @Mock
  private MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;

  @InjectMocks
  private UnknownArgumentsRule rule;

  @Test
  @DisplayName("Given the one or both of the first two arguments is UNKNOWN"
      + "When then method is called" +
      "Then the outcome should be FALSE")
  void isValid_true_valid_arguments() {

    assertThat(rule.isValid(new String[]{"validate", "unknown proofType", "number"})).isPresent();
    assertThat(rule.isValid(new String[]{"unknown command", "bank", "number"})).isPresent();
    assertThat(rule.isValid(new String[]{"nonsense", "bla blee blue"})).isPresent();
    assertThat(rule.isValid(new String[]{"nonsense", "bla blee blue"}).get().getMessage()).isEqualTo("Unknown command or proof type!");
  }

  @Test
  @DisplayName("Given the one or both of the first two arguments is UNKNOWN"
      + "When then method is called" +
      "Then Optional.empty() is returned")
  void isValid_false_invalid_arguments() {

    String[] validArgs1 = {"validate", "bsn", "number"};
    String[] validArgs2 = {"generate", "bank"};
    when(minimalNumberOfArgumentsRule.isValid(validArgs1)).thenReturn(Optional.empty());
    when(minimalNumberOfArgumentsRule.isValid(validArgs2)).thenReturn(Optional.empty());

    assertThat(rule.isValid(validArgs1)).isEmpty();
    assertThat(rule.isValid(validArgs2)).isEmpty();
  }

  @Test
  @DisplayName("Given any situation" +
      "When the method is called" +
      "Then is always returns true")
  void isApplicable_always_return_true() {

    String[] args = {"validate", "bsn", "number"};
    assertThat(rule.isApplicable(args)).isTrue();
  }
}