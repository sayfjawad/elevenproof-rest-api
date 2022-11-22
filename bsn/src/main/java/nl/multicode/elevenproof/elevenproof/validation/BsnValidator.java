package nl.multicode.elevenproof.elevenproof.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BsnValidator implements ConstraintValidator<BsnConstraint, String> {


    public boolean isValid(String bsn, ConstraintValidatorContext cxt) {
        return bsn != null && bsn.matches("[0-9]{9}") && isElfproef(bsn);
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