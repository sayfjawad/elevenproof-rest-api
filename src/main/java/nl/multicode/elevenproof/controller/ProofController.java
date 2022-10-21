package nl.multicode.elevenproof.controller;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;

public interface ProofController {

    void handleRequest(ProofType proofType, Command command, String number);
}
