package nl.multicode.elevenproof;

import java.util.List;
import java.util.Map;
import nl.multicode.elevenproof.controller.ElevenProofController;
import nl.multicode.elevenproof.generate.BankAccountGenerator;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.ElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.validate.BankAccountElevenProof;
import nl.multicode.elevenproof.validate.BsnElevenProof;
import nl.multicode.elevenproof.validate.input.InputValidator;
import nl.multicode.elevenproof.validate.input.MinimalNumberOfArgumentsRule;
import nl.multicode.elevenproof.validate.input.MissingValidateArgumentsRule;
import nl.multicode.elevenproof.validate.input.UnknownArgumentsRule;

public class App {

  public static final Map<ProofType, ElevenProofService> ELEVEN_PROOF_SERVICE_MAP = Map.of(
      ProofType.BANK_ACCOUNT, new BankAccountElevenProofService(new BankAccountGenerator(), new BankAccountElevenProof()),
      ProofType.BSN, new BurgerServiceNummerElevenProofService(new BurgerServiceNummerGenerator(), new BsnElevenProof()),
      ProofType.UNKNOWN, new UnknownElevenProofService()
  );
  public static final InputValidator INPUT_ARGUMENTSVALIDATE_RULE = new InputValidator(
      List.of(new MinimalNumberOfArgumentsRule(),
          new UnknownArgumentsRule(new MinimalNumberOfArgumentsRule()),
          new MissingValidateArgumentsRule(new MinimalNumberOfArgumentsRule())));

  private App(String[] args) {

    new ElevenProofController(INPUT_ARGUMENTSVALIDATE_RULE, ELEVEN_PROOF_SERVICE_MAP).handleRequest(args);
  }

  public static void main(String[] args) {

    new App(args);
  }
}
