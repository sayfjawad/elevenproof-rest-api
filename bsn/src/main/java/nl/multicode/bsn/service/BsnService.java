package nl.multicode.bsn.service;

import nl.multicode.bsn.model.BurgeServiceNummer;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.concurrent.ThreadLocalRandom;

@Service
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
            if (isValidBsn(new BurgeServiceNummer(randomBsn.toString()))) {
                return randomBsn.toString();
            }
        }
    }

    public boolean isValidBsn(BurgeServiceNummer bsn) {
        return validator.validate(bsn).size() == 0;
    }

}
