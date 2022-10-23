package nl.multicode.elevenproof.validation.input;

import nl.multicode.elevenproof.model.Command;

public class MissingValidateArgumentsRule implements ValidationRule<String[]> {

  @Override
  public boolean isValid(String[] args) {

    return isValidateCommand(args[0]) && isNotMissingNumberArgument(args);
  }

  private boolean isNotMissingNumberArgument(String[] args) {

    return args.length == 3;
  }

  private boolean isValidateCommand(String commandArgument) {

    return Command.VALIDATE.equals(Command.fromValue(commandArgument));
  }
}
