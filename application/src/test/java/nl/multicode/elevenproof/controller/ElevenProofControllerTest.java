package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import nl.multicode.elevenproof.map.StringToIntArrayMapper;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.UnknownElevenProofService;
import nl.multicode.elevenproof.util.LoggerExtension;
import nl.multicode.elevenproof.validate.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElevenProofControllerTest {

  public static final String BSN = "bsn";
  public static final String NUMBER = "123456788";
  public static final String VALIDATE = "validate";
  public static final String GENERATE = "generate";

  @RegisterExtension
  public LoggerExtension loggerExtension = new LoggerExtension();

  @Mock
  BankAccountElevenProofService bankAccountElevenProofService;
  @Mock
  BurgerServiceNummerElevenProofService burgerServiceNummerElevenProofService;
  @Mock
  UnknownElevenProofService unknownElevenProofService;
  @Mock
  InputValidator inputValidator;
  @Mock
  StringToIntArrayMapper stringToIntArrayMapper;

  ElevenProofController controller;

  @BeforeEach
  public void before() {

    controller = new ElevenProofController(inputValidator, Map.of(
        ProofType.BANK_ACCOUNT, bankAccountElevenProofService,
        ProofType.BSN, burgerServiceNummerElevenProofService,
        ProofType.UNKNOWN, unknownElevenProofService),
        stringToIntArrayMapper);
  }

  @Test
  @DisplayName("Given the command is generate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  generate command on the correct service")
  void handleRequest_generate_bsn() {

    controller.handleRequest(Command.GENERATE, ProofType.BSN, ElevenProofController.EMPTY_NUMBER);
    Mockito.verify(burgerServiceNummerElevenProofService).generate();
  }

  @Test
  @DisplayName("Given the command is generate "
      + "And proofType is BANK"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  generate command on the correct service")
  void handleRequest_generate_bank() {

    controller.handleRequest(Command.GENERATE, ProofType.BANK_ACCOUNT, ElevenProofController.EMPTY_NUMBER);
    Mockito.verify(bankAccountElevenProofService).generate();
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the validate command with the correct service")
  void handleRequest_validate_bsn() {

    controller.handleRequest(Command.VALIDATE, ProofType.BSN, ElevenProofController.EMPTY_NUMBER);
    Mockito.verify(burgerServiceNummerElevenProofService).isValid(ElevenProofController.EMPTY_NUMBER);
  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BANK"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the validate command with the correct service")
  void handleRequest_validate() {

    controller.handleRequest(Command.VALIDATE, ProofType.BANK_ACCOUNT, ElevenProofController.EMPTY_NUMBER);
    Mockito.verify(bankAccountElevenProofService).isValid(ElevenProofController.EMPTY_NUMBER);
  }

//  @Test
//  @DisplayName("Given the command is validate "
//      + "And proofType is UNKNOWN"
//      + "When handleRequest method is called"
//      + "Then the controller selects the correct service"
//      + "And runs the validate command with the correct service")
//  void handleRequest_unknown() {
//
//    controller.handleRequest(Command.UNKNOWN, ProofType.UNKNOWN, ElevenProofController.EMPTY_NUMBER);
//    assertThat(loggerExtension.getFormattedMessages()).contains("Cannot handle request: unknown, unknown, ");
//  }

//  @Test
//  @DisplayName("Given the wrong number if arguments is used "
//      + "When application is called"
//      + "Then the correct usage is logged")
//  void controller_args_missing_arguments() {
//
//    final var args = new String[]{"validate", "bsn"};
//    when(inputValidator.validate(args)).thenReturn(List.of(Mockito.mock(Error.class)));
//    controller.handleRequest(args);
//    assertThat(loggerExtension.getFormattedMessages()).contains("""
//        Usage is:
//        java -jar app.jar <validate> <bsn|bank> <number>
//        java -jar app.jar <generate> <bsn|bank>""");
//  }

  @Test
  @DisplayName("Given the command is validate "
      + "And proofType is BSN"
      + "When handleRequest method is called"
      + "Then the controller selects the correct service"
      + "And runs the  validate command on the correct service")
  void controller_handle_bsn_validate() {

    int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 8};
    when(stringToIntArrayMapper.apply(NUMBER)).thenReturn(ints);
    final var args = new String[]{VALIDATE, BSN, NUMBER};
    controller.handleRequest(args);
    Mockito.verify(burgerServiceNummerElevenProofService).isValid(ints);
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
    Mockito.verify(burgerServiceNummerElevenProofService).generate();
  }

//  @Test
//  @DisplayName("Given the command is wrong/unknown "
//      + "When handleRequest method is called"
//      + "Then the controller logs the error and logs informational message about the usage of the application")
//  void controller_handle_unknown() {
//
//    final var args = new String[]{"something wrong", "blie bla blue"};
//    controller.handleRequest(args);
//
//    assertThat(loggerExtension.getFormattedMessages()).contains(
//        """
//            Usage is:
//            java -jar app.jar <validate> <bsn|bank> <number>
//            java -jar app.jar <generate> <bsn|bank>""");
//  }
}