package nl.multicode.elevenproof.controller;

import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;

public interface ProofController {

  void handleRequest(String[] args);

  void handleRequest(Command command, ProofType proofType, String number);
}
