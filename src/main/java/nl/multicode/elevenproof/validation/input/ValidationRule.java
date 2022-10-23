package nl.multicode.elevenproof.validation.input;

public interface ValidationRule<T> {

  boolean isValid(T object);
}
