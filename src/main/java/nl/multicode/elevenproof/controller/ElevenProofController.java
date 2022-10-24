package nl.multicode.elevenproof.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.service.ElevenProofService;
import nl.multicode.elevenproof.validation.input.InputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class ElevenProofController implements ProofController {

    private static final Logger log = LogManager.getLogger(ElevenProofController.class);
    private final InputValidator inputValidator;
    private final Map<ProofType, ElevenProofService> elevenProofServices;

    @Override
    public void handleRequest(String[] args) {

        final var errorList = inputValidator.validate(args);
        if (errorList.isEmpty()) {
            final var command = Command.fromValue(args[0]);
            final var proofType = ProofType.fromValue(args[1]);
            switch (command) {
                case VALIDATE -> handleRequest(command, proofType, args[2]);
                case GENERATE -> handleRequest(command, proofType, null);
                case UNKNOWN -> logErrors(errorList);
            }
        } else {
            logErrors(errorList);
        }
    }

    @Override
    public void handleRequest(Command command, ProofType proofType, String number) {

        final var elevenProofService = elevenProofServices.get(proofType);

        switch (command) {
            case VALIDATE -> log.info("{} is {} {}", number, elevenProofService.isValid(number) ? "valid" : "invalid", proofType.getValue());
            case GENERATE -> log.info("Generated: {}", elevenProofService.generate());
            case UNKNOWN -> log.info("Cannot handle request: {}, {}, {}", proofType.getValue(), command.getValue(), number);
        }
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
