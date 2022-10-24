package nl.multicode.elevenproof.validation.input;

import java.util.Optional;

public class MinimalNumberOfArgumentsRule implements ValidationRule<String[]> {

  public static final int MINIMAL_NR_OF_ARGUMENTS = 2;

  @Override
  public Optional<Error> isValid(String[] args) {

    if (args != null && args.length >= MINIMAL_NR_OF_ARGUMENTS) {
      return Optional.empty();
    }
    return Optional.of(new Error("Number of supplied arguments is insufficient."));
  }

  /**
   * This implementation is always applicable and will be used alongside other combinations of input validation cases
   *
   * @param args input arguments
   * @return boolean
   */
  @Override
  public boolean isApplicable(String[] args) {

    return true;
  }
}
