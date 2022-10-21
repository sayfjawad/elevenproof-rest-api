package nl.multicode.elevenproof.controller;

import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.worker.BankAccountElevenProofWorker;
import nl.multicode.elevenproof.worker.BurgerServiceNummerElevenProofWorker;
import nl.multicode.elevenproof.worker.ElevenProofWorker;
import nl.multicode.elevenproof.worker.UnknownElevenProofWorker;

import java.util.Map;

public class ElevenProofWorkController implements WorkController {

    private static final Map<ProofType, ElevenProofWorker> elevenProofWorkers = Map.of(
            ProofType.BANK_ACCOUNT, new BankAccountElevenProofWorker(),
            ProofType.BSN, new BurgerServiceNummerElevenProofWorker(),
            ProofType.UNKNOWN, new UnknownElevenProofWorker()
    );

    @Override
    public void doWork(ElevenProofWork elevenProofWork) {
            elevenProofWorkers.get(elevenProofWork.getProofType())
                    .doWork(elevenProofWork);
    }
}
