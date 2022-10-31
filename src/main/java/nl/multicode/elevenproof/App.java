package nl.multicode.elevenproof;

import java.util.List;
import java.util.Map;
import nl.multicode.elevenproof.controller.ElevenProofController;
import nl.multicode.elevenproof.generator.BankAccountGenerator;
import nl.multicode.elevenproof.generator.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.validation.BankAccountElevenProof;
import nl.multicode.elevenproof.validation.BsnElevenProof;
import nl.multicode.elevenproof.validation.input.InputValidator;
import nl.multicode.elevenproof.validation.input.MinimalNumberOfArgumentsRule;
import nl.multicode.elevenproof.validation.input.MissingValidateArgumentsRule;
import nl.multicode.elevenproof.validation.input.UnknownArgumentsRule;

public class App {

    public static void main(String[] args) {

        final var elevenProofServiceMap = Map.of(
            ProofType.BANK_ACCOUNT, new BankAccountElevenProofService(new BankAccountGenerator(), new BankAccountElevenProof()),
            ProofType.BSN, new BurgerServiceNummerElevenProofService(new BurgerServiceNummerGenerator(), new BsnElevenProof()),
            ProofType.UNKNOWN, new UnknownElevenProofService()
        );
        final var inputArgumentsValidationRule = new InputValidator(
            List.of(new MinimalNumberOfArgumentsRule(),
                new UnknownArgumentsRule(new MinimalNumberOfArgumentsRule()),
                new MissingValidateArgumentsRule(new MinimalNumberOfArgumentsRule())));
        new ElevenProofController(inputArgumentsValidationRule, elevenProofServiceMap).handleRequest(args);
    }
}
