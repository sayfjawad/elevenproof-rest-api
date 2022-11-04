package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.util.TestAppender;
import nl.multicode.elevenproof.validate.input.InputValidator;
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

  public static final String BSN = "bsn";
  public static final String NUMBER = "number";
  public static final String VALIDATE = "validate";
  public static final String GENERATE = "generate";
  @Mock
  BankAccountElevenProofService bankAccountElevenProofService;
  @Mock
  BurgerServiceNummerElevenProofService burgerServiceNummerElevenProofService;
  @Mock
  UnknownElevenProofService unknownElevenProofService;

  @Mock
  InputValidator inputValidator;

  ElevenProofController controller;

  @BeforeEach
  public void before() {

    TestAppender.clear();
    controller = new ElevenProofController(inputValidator, Map.of(
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

    final var number = "null";
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

    final var number = "null";
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

  @Test
  void controller_args_missing_arguments() {

    final var args = new String[]{"validate", "bsn"};
    when(inputValidator.validate(args)).thenReturn(List.of(mock(Error.class)));
    controller.handleRequest(args);
    assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("""
        Usage is:
        java -jar app.jar <validate> <bsn|bank> <number>
        java -jar app.jar <generate> <bsn|bank>""");
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  validate command on the correct service")
  void controller_handle_bsn_validate() {

    final var args = new String[]{VALIDATE, BSN, NUMBER};
    controller.handleRequest(args);
    verify(burgerServiceNummerElevenProofService).isValid(args[2]);
  }

  @Test
  @DisplayName("Given the command is generate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  generate command on the correct service")
  void controller_handle_bsn_generate() {

    final var args = new String[]{GENERATE, BSN};
    controller.handleRequest(args);
    verify(burgerServiceNummerElevenProofService).generate();
  }

  @Test
  @DisplayName("Given the command is wrong/unknown "
      + "When handleRequest method is called"
      + "Then the controller logs the error and logs informational message about the usage of the application")
  void controller_handle_unknown() {

    final var args = new String[]{"something wrong", "blie bla blue"};
    controller.handleRequest(args);
    assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("""
        Usage is:
        java -jar app.jar <validate> <bsn|bank> <number>
        java -jar app.jar <generate> <bsn|bank>""");
  }
}