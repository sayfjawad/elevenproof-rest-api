package nl.multicode.elevenproof.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElevenProofWork {

    private final ProofType proofType;
    private final Command command;
    private final String number;
}
