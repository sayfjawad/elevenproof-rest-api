package nl.multicode.elevenproof.worker;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.service.BurgerServiceNummerElevenProofService;
import nl.multicode.elevenproof.service.ElevenProofService;

import static nl.multicode.elevenproof.model.ProofType.BSN;

@RequiredArgsConstructor
public class BurgerServiceNummerElevenProofWorker implements ElevenProofWorker {

    private final ElevenProofService elevenProofService;

    public BurgerServiceNummerElevenProofWorker() {
        this(new BurgerServiceNummerElevenProofService());
    }


    @Override
    public void doWork(ElevenProofWork work) {
        if (BSN.equals(work.getProofType())) {
            doWork(work, elevenProofService);
        }
    }
}
