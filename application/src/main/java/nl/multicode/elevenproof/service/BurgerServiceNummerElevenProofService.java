package nl.multicode.elevenproof.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.ElevenProof;

@RequiredArgsConstructor
public class BurgerServiceNummerElevenProofService implements ElevenProofService {

    private final Generator elevenproofGenerator;
    private final ElevenProof elevenProof;

    public Optional<int[]> generate() {

        return elevenproofGenerator.generate(ProofType.BSN);
    }

    public boolean isValid(int[] bsn) {

        return elevenProof.isElevenProof(bsn);
    }
}
