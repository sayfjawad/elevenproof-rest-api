package nl.multicode.elevenproof.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.multicode.elevenproof.map.StringToIntArrayMapper;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.ElevenProofService;
import nl.multicode.elevenproof.validate.InputValidator;

@Slf4j
@RequiredArgsConstructor
public class ElevenProofController implements ProofController {

  public static final int[] EMPTY_NUMBER = new int[0];
  private final InputValidator inputValidator;
  private final Map<ProofType, ElevenProofService> elevenProofServices;
  private final StringToIntArrayMapper intArrayMapper;

  @Override
  public void handleRequest(String[] args) {

    final var errorList = inputValidator.validate(args);
    if (errorList.isEmpty()) {
      final var command = Command.fromValue(args[0]);
      final var proofType = ProofType.fromValue(args[1]);
      switch (command) {
        case VALIDATE -> handleRequest(command, proofType, intArrayMapper.apply(args[2]));
        case GENERATE -> handleRequest(command, proofType, EMPTY_NUMBER);
        case UNKNOWN -> logErrors(errorList);
      }
    } else {
      logErrors(errorList);
    }
  }

  @Override
  public void handleRequest(Command command, ProofType proofType, int[] number) {

    final var elevenProofService = elevenProofServices.get(proofType);
    switch (command) {
      case VALIDATE -> {
        log.info("{} is {} {}", getStringFromIntArray(number), elevenProofService.isValid(number) ? "valid" : "invalid",
            proofType.getValue());
      }
      case GENERATE -> log.info("Generated: {}", elevenProofService.generate().map(this::getStringFromIntArray));
      case UNKNOWN -> log.info("Cannot handle request: {}, {}, {}", proofType.getValue(), command.getValue(), getStringFromIntArray(number));
    }
  }

  private String getStringFromIntArray(int[] number) {

    return Arrays.stream(number)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining());
  }

  private void logErrors(List<Error> errorList) {

    final var errorsMessage = errorList.stream()
        .map(Throwable::getMessage)
        .collect(Collectors.joining("\n"));
    log.error(errorsMessage);
    log.info("""
        Usage is:
        java -jar app.jar <validate> <bsn|bank> <number>
        java -jar app.jar <generate> <bsn|bank>""");
  }
}
