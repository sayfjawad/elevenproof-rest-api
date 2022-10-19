package nl.multicode.elevenproof.worker;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.ElevenProofWork;
import nl.multicode.elevenproof.service.BankAccountElevenProofService;
import nl.multicode.elevenproof.service.ElevenProofService;

import static nl.multicode.elevenproof.model.ProofType.BANK_ACCOUNT;

@RequiredArgsConstructor
public class BankAccountElevenProofWorker implements ElevenProofWorker {

    private final ElevenProofService elevenProofService;

    public BankAccountElevenProofWorker() {
        this(new BankAccountElevenProofService());
    }

    @Override
    public void doWork(ElevenProofWork work) {
        if (BANK_ACCOUNT.equals(work.getProofType())) {
            doWork(work, elevenProofService);
        }
    }
}
