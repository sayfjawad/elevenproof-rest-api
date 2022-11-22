package nl.multicode.elevenproof.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import javax.validation.Validator;
import nl.multicode.elevenproof.elevenproof.model.BurgeServiceNummer;
import org.junit.jupiter.api.Test;

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
        assertThat(bsnService.generateBsn()).matches("[0-9]{9}");
    }

    @Test
    public void validateBsn_true() {
        bsnService = new BsnService();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer("286158838"))).isTrue();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer("123456789"))).isFalse();
    }

    @Test
    public void validateBsn_false() {
        bsnService = new BsnService();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer("123456789"))).isFalse();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer("12345678"))).isFalse();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer("A12345678"))).isFalse();
        assertThat(bsnService.isValidBsn(new BurgeServiceNummer(null))).isFalse();
    }
}