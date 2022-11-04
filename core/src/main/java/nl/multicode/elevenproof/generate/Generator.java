package nl.multicode.elevenproof.generate;

import java.util.Optional;
import nl.multicode.elevenproof.model.ProofType;

public interface Generator {

  Optional<String> generate(ProofType proofType);

}
