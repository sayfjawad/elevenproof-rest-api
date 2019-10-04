package nl.multicode.bsn.service;

import nl.multicode.bsn.validation.ElfProef;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BsnServiceTest {

    private static final int EXPECTED_LENGTH = 9;

    @Mock
    private ElfProef elfProef;


    private BsnService bsnService;

    @Before
    public void setup() {
        bsnService = new BsnService(elfProef);
    }

    @Test
    public void generateBsn_default_constructor() {
        bsnService = new BsnService();
        String generatedBsn = bsnService.generateRandomBsnNummers();
        assertNotNull(generatedBsn);
        assertEquals(EXPECTED_LENGTH, generatedBsn.length());
    }

    @Test
    public void generateBsn() {
        when(elfProef.isElfproef(any())).thenReturn(Boolean.TRUE);
        assertNotNull(bsnService.generateRandomBsnNummers());
        assertEquals(EXPECTED_LENGTH, bsnService.generateRandomBsnNummers().length());
    }

    @Test
    public void validateBsn_true() {
        when(elfProef.isElfproef(any())).thenReturn(Boolean.TRUE);
        assertTrue(bsnService.isValidBsn("273279865"));
    }

    @Test
    public void validateBsn_false() {
        when(elfProef.isElfproef(any())).thenReturn(Boolean.FALSE);
        assertFalse(bsnService.isValidBsn("111111111"));
        assertFalse(bsnService.isValidBsn("XYZ111111"));
    }
}
