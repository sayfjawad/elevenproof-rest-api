package nl.multicode.elevenproof;

import nl.multicode.elevenproof.controller.ElevenProofWorkController;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.model.ProofType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        if (isValidArgs(args)) {

            ProofType proofType = ProofType.fromValue(args[1]);
            Command command = Command.fromValue(args[0]);
            String number = args.length == 3 ? args[2] : null;

            new ElevenProofWorkController().doWork(ElevenProofWork.builder()
                    .proofType(proofType)
                    .command(command)
                    .number(number)
                    .build());
            return;
        }

        log.info("""
                Usage is:
                java -jar app.jar <validate> <bsn|bank> <number>
                java -jar app.jar <generate> <bsn|bank>""");

    }

    private static boolean isValidArgs(String[] args) {
        if (args != null && args.length >= 2) {
            String commandArgument = args[0];
            String proofTypeArgument = args[1];
            return !Command.UNKNOWN.equals(Command.fromValue(commandArgument)) && !ProofType.UNKNOWN.equals(ProofType.fromValue(proofTypeArgument));
        }
        return false;
    }
}
