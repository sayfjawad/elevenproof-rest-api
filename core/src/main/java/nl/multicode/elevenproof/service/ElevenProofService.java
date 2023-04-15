package nl.multicode.elevenproof.service;

public interface ElevenProofService<T> {

  T generate();

  String validate(T number);
}
