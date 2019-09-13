package nl.multicode.bsn.service;

import nl.multicode.bsn.model.BurgeServiceNummer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BsnServiceTest {

    BsnService bsnService;

    @Before
    public void setup(){
        bsnService = new BsnService();
    }

    @Test
    public void generateBsn() {
        assertTrue(bsnService.generateBsn().matches("[0-9]{9}"));
    }

    @Test
    public void validateBsn_true() {
        assertTrue(bsnService.isValidBsn(new BurgeServiceNummer("286158838")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("123456789")));
    }

    @Test
    public void validateBsn_false() {
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("123456789")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("12345678")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer("A12345678")));
        assertFalse(bsnService.isValidBsn(new BurgeServiceNummer(null)));
    }
}