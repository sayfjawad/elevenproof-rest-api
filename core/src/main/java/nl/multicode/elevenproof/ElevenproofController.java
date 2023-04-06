package nl.multicode.elevenproof;

public interface ElevenproofController<T> {

  T generate();

  T validate(String number);

}
