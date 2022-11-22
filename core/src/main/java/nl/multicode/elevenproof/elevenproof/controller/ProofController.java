package nl.multicode.elevenproof.elevenproof.controller;

import nl.multicode.elevenproof.elevenproof.model.Command;
import nl.multicode.elevenproof.elevenproof.model.ProofType;

public interface ProofController {

  void handleRequest(String[] args);

  void handleRequest(Command command, ProofType proofType, int[] number);
}
