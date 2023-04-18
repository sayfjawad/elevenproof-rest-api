package nl.multicode.elevenproof.service;

public interface ElevenProofService<T> {

    T generate();

    boolean isValid(String number);
}
