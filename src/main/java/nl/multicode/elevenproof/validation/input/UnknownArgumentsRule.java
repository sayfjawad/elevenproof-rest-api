package nl.multicode.elevenproof.validation.input;

import java.util.Optional;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;

public class UnknownArgumentsRule implements ValidationRule<String[]> {

    @Override
    public Optional<Error> isValid(String[] args) {

        String commandArgument = args[0];
        String proofTypeArgument = args[1];
        if (isUnknownCommand(commandArgument) || isUnknownProofTypeArgument(proofTypeArgument)) {
            return Optional.of(new Error("Unknown command or proof type!"));
        }
        return Optional.empty();
    }

    @Override
    public boolean isApplicable(String[] args) {

        return true;
    }

    private boolean isUnknownProofTypeArgument(String proofTypeArgument) {

        return ProofType.UNKNOWN.equals(ProofType.fromValue(proofTypeArgument));
    }

    private boolean isUnknownCommand(String commandArgument) {

        return Command.UNKNOWN.equals(Command.fromValue(commandArgument));
    }
}
