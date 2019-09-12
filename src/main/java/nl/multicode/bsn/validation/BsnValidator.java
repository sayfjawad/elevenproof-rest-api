package nl.multicode.bsn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BsnValidator implements ConstraintValidator<BsnConstraint, String> {

    @Override
    public void initialize(BsnConstraint bsn) {
    }

    @Override
    public boolean isValid(String bsn, ConstraintValidatorContext cxt) {
        return bsn != null && bsn.matches("[0-9]{9}") && isElfProef(bsn);
    }


    private boolean isElfProef(String bsn) {
        long[] bsn_multipliers = {9, 8, 7, 6, 5, 4, 3, 2, -1};
        long[] bsn_digits = getDigits(bsn);
        long sum = 0;
        for (int i = 0; i < bsn_multipliers.length; i++) {
            sum += bsn_digits[i] * bsn_multipliers[i];
        }
        return sum % 11 == 0;
    }

    private long[] getDigits(String nummer) {
        long[] digits = new long[9];

        for (int i = nummer.length() - 1, j = 1; i >= 0; i--, j++) {
            digits[digits.length - j] = Character.getNumericValue(nummer.charAt(i));
        }
        return digits;
    }

}