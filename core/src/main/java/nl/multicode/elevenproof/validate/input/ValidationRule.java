package nl.multicode.elevenproof.validate.input;

import java.util.Optional;

public interface ValidationRule<T> {

    /**
     * Returns an Optional that can contain validation errors
     *
     * @param arguments to be validated
     * @return Optional<Error> error if found
     */
    Optional<Error> isValid(T arguments);

    /**
     * @param args arguments to be checked and evaluate if this rule appies to these arguments
     * @return boolean based on the arguments evaluation
     */
    boolean isApplicable(String[] args);
}
