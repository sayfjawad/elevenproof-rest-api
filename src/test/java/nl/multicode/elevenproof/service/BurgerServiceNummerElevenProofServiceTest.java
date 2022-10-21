package nl.multicode.elevenproof.service;

import nl.multicode.elevenproof.generator.Generator;
import nl.multicode.elevenproof.validation.ElevenProof;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerServiceNummerElevenProofServiceTest {

    private static final int EXPECTED_LENGTH = 9;

    @Mock
    private ElevenProof elevenProof;

    @Mock
    private Generator generator;

    private BurgerServiceNummerElevenProofService burgerServiceNummerElevenProofService;

    @BeforeEach
    public void setup() {
        burgerServiceNummerElevenProofService = new BurgerServiceNummerElevenProofService(generator, elevenProof);
    }

    @Test
    public void generateBsn_default_constructor() {
        burgerServiceNummerElevenProofService = new BurgerServiceNummerElevenProofService();
        var generatedBsn = burgerServiceNummerElevenProofService.generate();
        assertThat(generatedBsn).isNotEmpty();
        assertThat(generatedBsn.get().length()).isEqualTo(EXPECTED_LENGTH);
    }

    @Test
    public void validateBsn_false() {
        String bsn = "111111111";
        assertThat(burgerServiceNummerElevenProofService.isValid(bsn)).isFalse();
        verify(elevenProof).isElevenProof(bsn);
    }

    @Test
    public void validateBsn_true() {
        String bsn = "111111111";
        when(elevenProof.isElevenProof(bsn)).thenReturn(Boolean.TRUE);
        assertThat(burgerServiceNummerElevenProofService.isValid(bsn)).isTrue();
        verify(elevenProof).isElevenProof(bsn);
    }
}
