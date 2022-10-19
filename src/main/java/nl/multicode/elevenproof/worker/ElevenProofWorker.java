package nl.multicode.elevenproof.worker;

import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.service.ElevenProofService;

import static nl.multicode.elevenproof.model.Command.GENERATE;
import static nl.multicode.elevenproof.model.Command.VALIDATE;

public interface ElevenProofWorker {

    void doWork(ElevenProofWork work);

    default void doWork(ElevenProofWork work, ElevenProofService elevenProofService) {
        if (VALIDATE.equals(work.getCommand())) {
            final var number = work.getNumber();
            final var valid = elevenProofService.isValid(work.getNumber()) ? "valid" : "invalid";
            System.out.println(number + " is " + valid + " " + work.getProofType().getValue());
        } else if (GENERATE.equals(work.getCommand())) {
            System.out.println(elevenProofService.generate());
        }
    }
}
