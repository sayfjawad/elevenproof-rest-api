package nl.multicode.elevenproof.generator;

import nl.multicode.elevenproof.model.ProofType;

import java.util.Optional;

public interface Generator {

    Optional<String> generate(ProofType proofType);

}
