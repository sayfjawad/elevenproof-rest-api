package nl.multicode.elevenproof.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ElevenProofValidator implements ConstraintValidator<ElevenProofConstraint, String> {

  @Override
  public void initialize(ElevenProofConstraint elevenProofConstraint) {

  }

  @Override
  public boolean isValid(String elevenProofNumber, ConstraintValidatorContext cxt) {

    return elevenProofNumber != null && elevenProofNumber.matches("[0-9]{9}") && isElfproef(elevenProofNumber);
  }

  private boolean isElfproef(String bsn) {

    int checksum = 0;
    for (int index = 0; index < 8; index++) {
      checksum += (Integer.parseInt(Character.toString(bsn.charAt(index))) * (9 - index));
    }
    checksum -= Integer.parseInt(Character.toString(bsn.charAt(8)));

    return checksum % 11 == 0;
  }

}