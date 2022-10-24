package nl.multicode.elevenproof.validation.input;

import java.util.Optional;

public interface ValidationRule<T> {

  /**
   * Returns an Optional that can contain validation errors
   *
   * @param arguments
   * @return Optional<Error>
   */
  Optional<Error> isValid(T arguments);

  /**
   * @param args
   * @return
   */
  boolean isApplicable(String[] args);
}
