package nl.multicode.elevenproof.service;

import java.util.concurrent.ThreadLocalRandom;
import javax.validation.Validation;
import javax.validation.Validator;
import nl.multicode.elevenproof.model.ElevenProofNumber;

public class BsnService {

  private static final int MIN = 100_000_000;
  private static final int MAX = 999_999_998;

  private final Validator validator;

  public BsnService() {

    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  public BsnService(Validator validator) {

    this.validator = validator;
  }

  public String generateBsn() {

    while (true) {
      Long randomBsn = (long) ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
      if (isValidBsn(new ElevenProofNumber(randomBsn.toString()))) {
        return randomBsn.toString();
      }
    }
  }

  public boolean isValidBsn(ElevenProofNumber bsn) {

    return validator.validate(bsn).size() == 0;
  }

}
