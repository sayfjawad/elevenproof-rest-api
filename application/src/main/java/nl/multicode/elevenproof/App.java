package nl.multicode.elevenproof;

import java.util.List;
import java.util.Map;
import nl.multicode.elevenproof.controller.ElevenProofController;
import nl.multicode.elevenproof.generate.BankAccountGenerator;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArrayMapper;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.ElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.validate.InputValidator;
import nl.multicode.elevenproof.validate.proof.BankAccountElevenProof;
import nl.multicode.elevenproof.validate.proof.BsnElevenProof;
import nl.multicode.elevenproof.validate.rule.MinimalNumberOfArgumentsRule;
import nl.multicode.elevenproof.validate.rule.MissingValidateArgumentsRule;
import nl.multicode.elevenproof.validate.rule.UnknownArgumentsRule;

public class App {

    private static final StringToIntArrayMapper INT_ARRAY_MAPPER = new StringToIntArrayMapper();

    private static final BankAccountGenerator BANKACCOUNT_GENERATOR = new BankAccountGenerator();
    private static final BurgerServiceNummerGenerator BSN_GENERATOR = new BurgerServiceNummerGenerator();

    private static final BankAccountElevenProof BANK_ACCOUNT_ELEVEN_PROOF = new BankAccountElevenProof();
    private static final BsnElevenProof BSN_ELEVEN_PROOF = new BsnElevenProof();

    private static final MinimalNumberOfArgumentsRule MINIMAL_NUMBER_OF_ARGUMENTS_RULE = new MinimalNumberOfArgumentsRule();
    private static final MissingValidateArgumentsRule MISSING_VALIDATE_ARGUMENTS_RULE = new MissingValidateArgumentsRule(
        MINIMAL_NUMBER_OF_ARGUMENTS_RULE);
    private static final UnknownArgumentsRule UNKNOWN_ARGUMENTS_RULE = new UnknownArgumentsRule(MINIMAL_NUMBER_OF_ARGUMENTS_RULE);
    private static final InputValidator INPUT_ARGUMENTSVALIDATE_RULE = new InputValidator(
        List.of(MINIMAL_NUMBER_OF_ARGUMENTS_RULE,
            UNKNOWN_ARGUMENTS_RULE,
            MISSING_VALIDATE_ARGUMENTS_RULE));
    private static final Map<ProofType, ElevenProofService> ELEVEN_PROOF_SERVICE_MAP = Map.of(
        ProofType.BANK_ACCOUNT, new BankAccountElevenProofService(BANKACCOUNT_GENERATOR, BANK_ACCOUNT_ELEVEN_PROOF),
        ProofType.BSN, new BurgerServiceNummerElevenProofService(BSN_GENERATOR, BSN_ELEVEN_PROOF),
        ProofType.UNKNOWN, new UnknownElevenProofService()
    );
    private static final ElevenProofController ELEVEN_PROOF_CONTROLLER = new ElevenProofController(
        INPUT_ARGUMENTSVALIDATE_RULE,
        ELEVEN_PROOF_SERVICE_MAP,
        INT_ARRAY_MAPPER);

    private App(String[] args) {

        ELEVEN_PROOF_CONTROLLER.handleRequest(args);
    }

    public static void main(String[] args) {

        new App(args);
    }
}
