package nl.multicode.elevenproof.elevenproof.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.elevenproof.service.BsnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BsnControllerTest {

    @Mock
    BsnService bsnService;

    @InjectMocks
    BsnController bsnController;

    @Test
    public void validateBsn() {
        when(bsnService.isValidBsn(any())).thenReturn(Boolean.TRUE);
        assertThat(bsnController.validateBsn("bsn")).isTrue();
        verify(bsnService).isValidBsn(any());
    }

    @Test
    public void generateBsn() {
        when(bsnService.generateBsn()).thenReturn("generatedBsn");
        assertThat(bsnController.generateBsn()).isEqualTo("generatedBsn");
        verify(bsnService).generateBsn();
    }
}