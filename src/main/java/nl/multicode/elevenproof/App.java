package nl.multicode.elevenproof;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.worker.BankAccountElevenProofWorker;
import nl.multicode.elevenproof.worker.BurgerServiceNummerElevenProofWorker;
import nl.multicode.elevenproof.worker.ElevenProofWorker;

import java.util.List;

public class App {

    private static final List<ElevenProofWorker> elevenProofWorkers = List.of(
            new BankAccountElevenProofWorker(),
            new BurgerServiceNummerElevenProofWorker()
    );

    public static void main(String[] args) {
        if (isValidArgs(args)) {

            final var work = ElevenProofWork.builder()
                    .proofType(ProofType.fromValue(args[1]))
                    .command(Command.fromValue(args[0]))
                    .number(args.length == 3 ? args[2] : null)
                    .build();

            elevenProofWorkers.forEach(worker -> worker.doWork(work));
        }
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
