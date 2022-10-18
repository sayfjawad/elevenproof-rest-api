package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.service.supplier.RandomDigitsStringSupplier;
import nl.multicode.elevenproof.validation.ElevenProof;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BsnServiceTest {

    private static final int EXPECTED_LENGTH = 9;

    @Mock
    private ElevenProof elevenProof;

    @Mock
    private RandomDigitsStringSupplier randomDigitsStringSupplier;

    private BsnService bsnService;

    @BeforeEach
    public void setup() {
        bsnService = new BsnService(elevenProof, randomDigitsStringSupplier);
    }

    @Test
    public void generateBsn_default_constructor() {
        bsnService = new BsnService();
        var generatedBsn = bsnService.generateRandomBsnNummers();
        assertThat(generatedBsn).isNotEmpty();
        assertThat(generatedBsn.get().length()).isEqualTo(EXPECTED_LENGTH);
    }

    @Test
    public void generateBsn() {
        String bsn = "123456789";
        when(randomDigitsStringSupplier.supply()).thenReturn(bsn);
        when(elevenProof.isElevenProof(bsn)).thenReturn(Boolean.TRUE);
        bsnService.generateRandomBsnNummers();
        verify(elevenProof).isElevenProof(bsn);
        verify(randomDigitsStringSupplier).supply();
    }

    @Test
    public void validateBsn_false() {
        String bsn = "111111111";
        assertThat(bsnService.isValidBsn(bsn)).isFalse();
        verify(elevenProof).isElevenProof(bsn);
    }

    @Test
    public void validateBsn_true() {
        String bsn = "111111111";
        when(elevenProof.isElevenProof(bsn)).thenReturn(Boolean.TRUE);
        assertThat(bsnService.isValidBsn(bsn)).isTrue();
        verify(elevenProof).isElevenProof(bsn);
    }
}
