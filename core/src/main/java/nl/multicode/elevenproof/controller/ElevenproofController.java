package nl.multicode.elevenproof.controller;

public interface ElevenproofController<T> {

    T generate();

    T validate(String number);


}
