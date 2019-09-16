package nl.multicode.bsn.service;

import nl.multicode.bsn.model.BurgeServiceNummer;
import org.junit.Test;

import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BsnServiceTest {

    BsnService bsnService;
    Validator validator;

    @Test
    public void generateBsn_found() {
        validator = mock(Validator.class);
        bsnService = new BsnService(validator);
        Set emptySet = mock(Set.class);
        when(emptySet.size()).thenReturn(0);
        when(validator.validate(any())).thenReturn(emptySet);
        bsnService.generateBsn();
        verify(validator).validate(any());
    }

    @Test
    public void generateBsn_generated() {
        bsnService = new BsnService();
        assertTrue(bsnService.generateBsn().matches("[0-9]{9}"));
    }

    @Test
    public void validateBsn_true() {
        bsnService = new BsnService();
        assertTrue(bsnService.isValidBsn(new BurgeServiceNummer("286158838")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("123456789")));
    }

    @Test
    public void validateBsn_false() {
        bsnService = new BsnService();
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("123456789")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("12345678")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("A12345678")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer(null)));
    }
}