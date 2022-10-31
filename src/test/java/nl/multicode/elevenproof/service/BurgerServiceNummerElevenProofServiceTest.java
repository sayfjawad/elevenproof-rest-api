package nl.multicode.elevenproof.service;

import static org.mockito.Mockito.verify;

import nl.multicode.elevenproof.generator.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validation.ElevenProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BurgerServiceNummerElevenProofServiceTest {

    @Mock
    private Generator elevenproofGenerator;
    @Mock
    private ElevenProof elevenProof;
    @InjectMocks
    private BurgerServiceNummerElevenProofService service;

    @Test
    void generate() {

        service.generate();
        verify(elevenproofGenerator).generate(ProofType.BSN);
    }

    @Test
    void isValid() {

        final var bsn = "bsn";
        service.isValid(bsn);
        verify(elevenProof).isElevenProof(bsn);
    }
}