package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Map;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.util.TestAppender;
import nl.multicode.elevenproof.validation.input.InputValidator;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElevenProofControllerTest {

  @Mock
  BankAccountElevenProofService bankAccountElevenProofService;
  @Mock
  BurgerServiceNummerElevenProofService burgerServiceNummerElevenProofService;
  @Mock
  UnknownElevenProofService unknownElevenProofService;

  @Mock
  InputValidator rule;

  ElevenProofController controller;

  @BeforeEach
  public void before() {

    TestAppender.clear();
    controller = new ElevenProofController(rule, Map.of(
        ProofType.BANK_ACCOUNT, bankAccountElevenProofService,
        ProofType.BSN, burgerServiceNummerElevenProofService,
        ProofType.UNKNOWN, unknownElevenProofService
    ));
  }

  @AfterEach
  public void tearDown() {

    TestAppender.clear();
  }

  @Test
  @DisplayName("Given the command is generate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  generate command on the correct service")
  void handleRequest_generate_bsn() {

    controller.handleRequest(Command.GENERATE, ProofType.BSN, null);
    verify(burgerServiceNummerElevenProofService).generate();
  }

  @Test
  @DisplayName("Given the command is generate "
      + "And proofType is BANK"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  generate command on the correct service")
  void handleRequest_generate_bank() {

    controller.handleRequest(Command.GENERATE, ProofType.BANK_ACCOUNT, null);
    verify(bankAccountElevenProofService).generate();
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the validate command with the correct service")
  void handleRequest_validate_bsn() {

    String number = "null";
    controller.handleRequest(Command.VALIDATE, ProofType.BSN, number);
    verify(burgerServiceNummerElevenProofService).isValid(number);
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BANK"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the validate command with the correct service")
  void handleRequest_validate() {

    String number = "null";
    controller.handleRequest(Command.VALIDATE, ProofType.BANK_ACCOUNT, number);
    verify(bankAccountElevenProofService).isValid(number);
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is UNKNIWN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the validate command with the correct service")
  void handleRequest_unknown() {

    controller.handleRequest(Command.UNKNOWN, ProofType.UNKNOWN, "null");
    assertThat(TestAppender.getLogs(Level.INFO)).contains("Cannot handle request: unknown, unknown, null");
  }
}