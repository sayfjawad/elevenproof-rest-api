package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.service.supplier.Random9DigitsStringSupplier;
import nl.multicode.elevenproof.validation.ElevenProof;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BsnElevenProofServiceTest {

    private static final int EXPECTED_LENGTH = 9;

    @Mock
    private ElevenProof elevenProof;

    @Mock
    private Random9DigitsStringSupplier random9DigitsStringSupplier;

    private BsnElevenProofService bsnElevenProofService;

    @BeforeEach
    public void setup() {
        bsnElevenProofService = new BsnElevenProofService(elevenProof, random9DigitsStringSupplier);
    }

    @Test
    public void generateBsn_default_constructor() {
        bsnElevenProofService = new BsnElevenProofService();
        var generatedBsn = bsnElevenProofService.generate();
        assertThat(generatedBsn).isNotEmpty();
        assertThat(generatedBsn.get().length()).isEqualTo(EXPECTED_LENGTH);
    }

    @Test
    public void generateBsn() {
        String bsn = "123456789";
        when(random9DigitsStringSupplier.supply()).thenReturn(bsn);
        when(elevenProof.isElevenProof(bsn)).thenReturn(Boolean.TRUE);
        bsnElevenProofService.generate();
        verify(elevenProof).isElevenProof(bsn);
        verify(random9DigitsStringSupplier).supply();
    }

    @Test
    public void validateBsn_false() {
        String bsn = "111111111";
        assertThat(bsnElevenProofService.isValid(bsn)).isFalse();
        verify(elevenProof).isElevenProof(bsn);
    }

    @Test
    public void validateBsn_true() {
        String bsn = "111111111";
        when(elevenProof.isElevenProof(bsn)).thenReturn(Boolean.TRUE);
        assertThat(bsnElevenProofService.isValid(bsn)).isTrue();
        verify(elevenProof).isElevenProof(bsn);
    }
}
