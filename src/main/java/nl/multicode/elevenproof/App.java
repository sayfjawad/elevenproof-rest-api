package nl.multicode.elevenproof;

import java.util.Map;
import nl.multicode.elevenproof.controller.ElevenProofController;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.ElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.validation.input.InputArgumentsValidationRule;
import nl.multicode.elevenproof.validation.input.MinimalNumberOfArgumentsRule;
import nl.multicode.elevenproof.validation.input.MissingValidateArgumentsRule;
import nl.multicode.elevenproof.validation.input.UnknownArgumentsRule;

public class App {

  public static void main(String[] args) {

    Map<ProofType, ElevenProofService> elevenProofServiceMap = Map.of(
        ProofType.BANK_ACCOUNT, new BankAccountElevenProofService(),
        ProofType.BSN, new BurgerServiceNummerElevenProofService(),
        ProofType.UNKNOWN, new UnknownElevenProofService()
    );
    InputArgumentsValidationRule inputArgumentsValidationRule = new InputArgumentsValidationRule(
        new MinimalNumberOfArgumentsRule(),
        new UnknownArgumentsRule(),
        new MissingValidateArgumentsRule());
    new ElevenProofController(inputArgumentsValidationRule, elevenProofServiceMap).handleRequest(args);
  }
}
