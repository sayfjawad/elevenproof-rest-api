package nl.multicode.elevenproof.validation.input;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class InputArgumentsValidationRule implements ValidationRule<String[]> {

  private static final Logger log = LogManager.getLogger(InputArgumentsValidationRule.class);

  private final MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;
  private final UnknownArgumentsRule unknownArgumentsRule;
  private final MissingValidateArgumentsRule missingValidateArgumentsRule;

  @Override
  public boolean isValid(String[] args) {

    boolean isValid = false;

    if (minimalNumberOfArgumentsRule.isValid(args) && unknownArgumentsRule.isValid(args)) {
      Command command = Command.fromValue(args[0]);
      switch (command) {
        case VALIDATE -> isValid = missingValidateArgumentsRule.isValid(args);
        case GENERATE -> isValid = true;
        case UNKNOWN -> log.warn("Unknown command is not supported: {}", args[0]);
      }
    }
    return isValid;
  }


}
