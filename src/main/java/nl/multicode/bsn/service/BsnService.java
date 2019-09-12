package nl.multicode.bsn.service;

import nl.multicode.bsn.model.BurgeServiceNummer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BsnService {

    private final Validator validator;

    public BsnService() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public String generateBsn() {
        Long bsn = null;
        while (bsn == null) {
            Long randomNumber = (long) ThreadLocalRandom.current().nextInt(100_000_000, 999_999_998 + 1);
            if (isValidBsn(new BurgeServiceNummer(randomNumber.toString()))) {
                bsn = randomNumber;
            }
        }
        return bsn.toString();
    }

    public boolean isValidBsn(BurgeServiceNummer bsn) {
        return validator.validate(bsn).size() == 0;
    }

}
