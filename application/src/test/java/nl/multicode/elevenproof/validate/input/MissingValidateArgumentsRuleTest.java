package nl.multicode.elevenproof.validate.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.multicode.elevenproof.validate.rule.MinimalNumberOfArgumentsRule;
import nl.multicode.elevenproof.validate.rule.MissingValidateArgumentsRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MissingValidateArgumentsRuleTest {

    @Mock
    private MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;

    @InjectMocks
    private MissingValidateArgumentsRule rule;

    @Test
    @DisplayName("Given the command is 'validate'" +
        "And args contains 3 arguments" +
        "Then the should not contain an error")
    void isValid_no_error() {

        assertThat(rule.isValid(new String[]{"validate", "bsn", "some bsn"})).isEmpty();
    }

    @Test
    @DisplayName("Given the command is 'validate'" +
        "And args contains more or less than 3 arguments" +
        "Then an Optional<Error> is returned")
    void isValid_error() {

        assertThat(rule.isValid(new String[]{"validate", "bsn"})).isPresent();
        assertThat(rule.isValid(new String[]{"validate"}).get().getMessage()).isEqualTo("Validate needs 3 arguments. Only 1 supplied");
    }

    @Test
    @DisplayName("Given the method is called" +
        "When command argument is validate" +
        "And minimal number of arguments is met" +
        "Then method returns returns true")
    void isApplicable_true() {

        String[] args1 = {"validate", "bsn", "number"};
        when(minimalNumberOfArgumentsRule.isValid(any())).thenReturn(Optional.empty());
        assertThat(rule.isApplicable(args1)).isTrue();
    }

    @Test
    @DisplayName("Given the method is called" +
        "When command argument is NOT validate" +
        "And minimal number of arguments is met" +
        "Then method returns returns false")
    void isApplicable_false() {

        String[] args1 = {"generate", "bsn", "number"};
        when(minimalNumberOfArgumentsRule.isValid(any())).thenReturn(Optional.empty());
        assertThat(rule.isApplicable(args1)).isFalse();
    }
}