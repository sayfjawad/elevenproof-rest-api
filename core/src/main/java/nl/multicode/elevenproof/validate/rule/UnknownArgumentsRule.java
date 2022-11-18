package nl.multicode.elevenproof.validate.rule;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.input.ValidationRule;

@RequiredArgsConstructor
public class UnknownArgumentsRule implements ValidationRule<String[]> {

    private final MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;

    @Override
    public Optional<Error> isValid(String[] args) {

        if (minimalNumberOfArgumentsRule.isValid(args).isEmpty()
            && (isUnknownCommand(args[0]) || isUnknownProofTypeArgument(args[1]))) {
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
