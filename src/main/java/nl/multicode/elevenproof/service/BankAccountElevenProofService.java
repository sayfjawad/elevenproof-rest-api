package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generator.BankAccountGenerator;
import nl.multicode.elevenproof.generator.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.BankAccountElevenProof;
import nl.multicode.elevenproof.validation.ElevenProof;

import java.util.Optional;

@RequiredArgsConstructor
public class BankAccountElevenProofService implements ElevenProofService {

    private final Generator elevenproofGenerator;
    private final ElevenProof elevenProof;

    public BankAccountElevenProofService() {
        this(new BankAccountGenerator(), new BankAccountElevenProof());
    }

    public Optional<String> generate() {

        return elevenproofGenerator.generate(ProofType.BANK_ACCOUNT);
    }

    public Boolean isValid(String bankAccount) {
        return elevenProof.isElevenProof(bankAccount);
    }
}
