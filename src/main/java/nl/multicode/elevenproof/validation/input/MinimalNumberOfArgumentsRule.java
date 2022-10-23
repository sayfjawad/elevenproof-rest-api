package nl.multicode.elevenproof.validation.input;

public class MinimalNumberOfArgumentsRule implements ValidationRule<String[]> {

  public static final int MINIMAL_NR_OF_ARGUMENTS = 2;

  @Override
  public boolean isValid(String[] args) {

    return args != null && args.length >= MINIMAL_NR_OF_ARGUMENTS;
  }
}
