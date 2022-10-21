package nl.multicode.elevenproof;

import nl.multicode.elevenproof.controller.ElevenProofController;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        if (isInvalidArgs(args)) {
            log.info("""
                    Usage is:
                    java -jar app.jar <validate> <bsn|bank> <number>
                    java -jar app.jar <generate> <bsn|bank>""");

            return;
        }

        ProofType proofType = ProofType.fromValue(args[1]);
        Command command = Command.fromValue(args[0]);
        String number = args.length == 3 ? args[2] : null;

        new ElevenProofController(Map.of(
                ProofType.BANK_ACCOUNT, new BankAccountElevenProofService(),
                ProofType.BSN, new BurgerServiceNummerElevenProofService(),
                ProofType.UNKNOWN, new UnknownElevenProofService()
        )).handleRequest(proofType, command, number);
    }

    private static boolean isInvalidArgs(String[] args) {
        boolean invalidArgs = true;

        if (args != null && args.length >= 2) {
            String commandArgument = args[0];
            String proofTypeArgument = args[1];
            boolean unknownCommand = isNotUnknownCommand(commandArgument);
            boolean unknownProofType = isNotUnknownProofTypeArgument(proofTypeArgument);
            boolean missingArgument = isValidateCommand(commandArgument) && isMissingNumberArgument(args);
            invalidArgs = (unknownCommand || unknownProofType || missingArgument);
        }
        return invalidArgs;
    }

    private static boolean isMissingNumberArgument(String[] args) {
        return args.length < 3;
    }

    private static boolean isNotUnknownProofTypeArgument(String proofTypeArgument) {
        return ProofType.UNKNOWN.equals(ProofType.fromValue(proofTypeArgument));
    }

    private static boolean isNotUnknownCommand(String commandArgument) {
        return Command.UNKNOWN.equals(Command.fromValue(commandArgument));
    }

    private static boolean isValidateCommand(String commandArgument) {
        return Command.VALIDATE.equals(Command.fromValue(commandArgument));
    }
}
