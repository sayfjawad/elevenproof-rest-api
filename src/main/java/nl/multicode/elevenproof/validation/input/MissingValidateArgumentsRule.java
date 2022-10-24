package nl.multicode.elevenproof.validation.input;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.Command;

@RequiredArgsConstructor
public class MissingValidateArgumentsRule implements ValidationRule<String[]> {

    private final MinimalNumberOfArgumentsRule minimalNumberOfArgumentsRule;

    @Override
    public Optional<Error> isValid(String[] args) {

        if (isValidateCommand(args[0])
            && isMissingNumberArgument(args)) {
            return Optional.of(new Error("Validate needs 3 arguments. Only " + args.length + " supplied"));
        }
        return Optional.empty();
    }

    /**
     * Will only return true if the arguments have the minimal requirements and the command is VALIDATE
     *
     * @param args input arguments array
     * @return errors if any
     */
    @Override
    public boolean isApplicable(String[] args) {

        String commandArgument = args[0];
        return minimalNumberOfArgumentsRule.isValid(args).isEmpty() && isValidateCommand(commandArgument);
    }

    private boolean isMissingNumberArgument(String[] args) {

        return args.length < 3;
    }

    private boolean isValidateCommand(String commandArgument) {

        return Command.VALIDATE.equals(Command.fromValue(commandArgument));
    }
}
