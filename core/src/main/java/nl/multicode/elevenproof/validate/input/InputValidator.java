package nl.multicode.elevenproof.validate.input;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InputValidator {

  private final List<ValidationRule<String[]>> validationRules;

  public List<Error> validate(final String[] args) {

    return validationRules.stream()
        .filter(validationRule -> validationRule.isApplicable(args))
        .map(validationRule -> validationRule.isValid(args))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }
}
