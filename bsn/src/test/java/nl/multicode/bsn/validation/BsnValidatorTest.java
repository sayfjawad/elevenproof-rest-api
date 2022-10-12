package nl.multicode.bsn.validation;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.Validation;
import javax.validation.Validator;
import nl.multicode.bsn.model.BurgeServiceNummer;
import org.junit.jupiter.api.Test;

public class BsnValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static final String VALID_BSN = "142279675";
    private static final String[] INVALID_BSN = {"123456789", "A23456789", "1234567", "%$#@", null};


    @Test
    public void test_valid() {
        assertThat(validator.validate(new BurgeServiceNummer(VALID_BSN))).isEmpty();
    }

    @Test
    public void test_invalid() {
        for (String invalideBSN : INVALID_BSN) {
            assertThat(validator.validate(new BurgeServiceNummer(invalideBSN))).isNotEmpty();
        }
    }

}