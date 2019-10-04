package nl.multicode.bsn.validation;

import nl.multicode.bsn.model.BurgeServiceNummer;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.*;

public class BsnValidatorTest {

     private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

     private static final String VALID_BSN = "142279675";
     private static final String INVALID_BSN1 = "123456789";
     private static final String INVALID_BSN2 = "A23456789";
     private static final String INVALID_BSN3 = "1234567";
     private static final String INVALID_BSN4 = "%$#@";
     private static final String INVALID_BSN5 = null;


    @Test
    public void test_valid(){
        assertTrue(validator.validate(new BurgeServiceNummer(VALID_BSN)).isEmpty());
    }

    @Test
    public void test_invalid(){
        assertFalse(validator.validate(new BurgeServiceNummer(INVALID_BSN1)).isEmpty());
        assertFalse(validator.validate(new BurgeServiceNummer(INVALID_BSN2)).isEmpty());
        assertFalse(validator.validate(new BurgeServiceNummer(INVALID_BSN3)).isEmpty());
        assertFalse(validator.validate(new BurgeServiceNummer(INVALID_BSN4)).isEmpty());
        assertFalse(validator.validate(new BurgeServiceNummer(INVALID_BSN5)).isEmpty());
        assertEquals("BSN must be a 9 character numeric value", validator.validate(new BurgeServiceNummer(INVALID_BSN5)).iterator().next().getMessage());
    }

}