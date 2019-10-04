package nl.multicode.bsn.validation;

import nl.multicode.bsn.model.BurgeServiceNummer;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BsnValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static final String VALID_BSN = "142279675";
    private static final String[] INVALID_BSN = {"123456789", "A23456789", "1234567", "%$#@", null};


    @Test
    public void test_valid() {
        assertTrue(validator.validate(new BurgeServiceNummer(VALID_BSN)).isEmpty());
    }

    @Test
    public void test_invalid() {
        for (String invalideBSN : INVALID_BSN) {
            assertFalse(validator.validate(new BurgeServiceNummer(invalideBSN)).isEmpty());
        }
    }

}