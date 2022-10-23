package nl.multicode.elevenproof.generator;

import java.util.Optional;
import nl.multicode.elevenproof.model.ProofType;

public interface Generator {

  Optional<String> generate(ProofType proofType);

}
