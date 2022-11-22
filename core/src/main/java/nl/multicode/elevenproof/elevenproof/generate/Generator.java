package nl.multicode.elevenproof.elevenproof.generate;

import java.util.Optional;
import nl.multicode.elevenproof.elevenproof.model.ProofType;

public interface Generator {

  Optional<int[]> generate(ProofType proofType);

}
