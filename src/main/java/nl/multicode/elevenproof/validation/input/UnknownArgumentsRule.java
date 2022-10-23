package nl.multicode.elevenproof.validation.input;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;

public class UnknownArgumentsRule implements ValidationRule<String[]> {

  @Override
  public boolean isValid(String[] args) {

    String commandArgument = args[0];
    String proofTypeArgument = args[1];
    return !(isUnknownCommand(commandArgument) || isUnknownProofTypeArgument(proofTypeArgument));
  }

  private boolean isUnknownProofTypeArgument(String proofTypeArgument) {

    return ProofType.UNKNOWN.equals(ProofType.fromValue(proofTypeArgument));
  }

  private boolean isUnknownCommand(String commandArgument) {

    return Command.UNKNOWN.equals(Command.fromValue(commandArgument));
  }
}
