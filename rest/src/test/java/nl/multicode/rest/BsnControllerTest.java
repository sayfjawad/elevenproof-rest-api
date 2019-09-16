package nl.multicode.rest;

import nl.multicode.bsn.service.BsnService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BsnControllerTest {
    @Mock
    BsnService bsnService;

    @InjectMocks
    BsnController bsnController;

    @Test
    public void validateBsn() {
        when(bsnService.isValidBsn(any())).thenReturn(Boolean.TRUE);
        assertTrue(bsnController.validateBsn("bsn"));
        verify(bsnService).isValidBsn(any());
    }

    @Test
    public void generateBsn() {
        when(bsnService.generateBsn()).thenReturn("generatedBsn");
        assertEquals("generatedBsn", bsnController.generateBsn());
        verify(bsnService).generateBsn();
    }
}