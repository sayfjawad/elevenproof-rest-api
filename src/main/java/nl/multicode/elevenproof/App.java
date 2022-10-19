package nl.multicode.elevenproof;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BsnElevenProofService;

import static nl.multicode.elevenproof.model.Command.GENERATE;
import static nl.multicode.elevenproof.model.Command.VALIDATE;
import static nl.multicode.elevenproof.model.ProofType.BSN;


public class App {

    public static void main(String[] args) {
        if (isValidArgs(args)) {
            final Command command = Command.fromValue(args[0]);
            final ProofType proofType = ProofType.fromValue(args[1]);

            if (BSN.equals(proofType)) {
                BsnElevenProofService bsnElevenProofService = new BsnElevenProofService();
                if (VALIDATE.equals(command)) {
                    final String number = args[2];
                    String valid = bsnElevenProofService.isValid(number) ? "valid" : "invalid";
                    System.out.println(number + " is " + valid + " " + proofType.getValue());
                } else if (GENERATE.equals(command)) {
                    System.out.println(bsnElevenProofService.generate());
                }
            }
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
