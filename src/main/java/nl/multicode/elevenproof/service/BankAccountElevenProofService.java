package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generator.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.ElevenProof;

@RequiredArgsConstructor
public class BankAccountElevenProofService implements ElevenProofService {

    private final Generator elevenproofGenerator;
    private final ElevenProof elevenProof;

    public Optional<String> generate() {

        return elevenproofGenerator.generate(ProofType.BANK_ACCOUNT);
    }

    public boolean isValid(String bankAccount) {

        return elevenProof.isElevenProof(bankAccount);
    }
}
