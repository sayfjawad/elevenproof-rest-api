package nl.multicode.elevenproof.map;

public interface CustomMapper<I, O> {

  O map(I input);
}
