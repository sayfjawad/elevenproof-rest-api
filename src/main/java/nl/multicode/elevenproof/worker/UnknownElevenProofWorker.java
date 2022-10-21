package nl.multicode.elevenproof.worker;

import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.service.ElevenProofService;

public class UnknownElevenProofWorker implements ElevenProofWorker{
    @Override
    public void doWork(ElevenProofWork work) {
        // do nothing
    }

    @Override
    public void doWork(ElevenProofWork work, ElevenProofService elevenProofService) {
        // do nothing
    }
}
