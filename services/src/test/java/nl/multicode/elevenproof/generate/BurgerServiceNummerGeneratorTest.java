package nl.multicode.elevenproof.generate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BurgerServiceNummerGeneratorTest {

    @Mock
    private ObjectSupplier<int[]> randomDigitsSupplier;

    @Mock
    private IntArrayToString intArrayToString;

    @Mock
    private ElevenProof numberElevenProof;

    @InjectMocks
    private BurgerServiceNummerGenerator generator;

    @Test
    @DisplayName("Given the generator is implemented correctly"
            + "When the generate() method is called withe the correct ProofType"
            + "Then a valid eleven-proof BSN number is generated and returned")
    void generate() {

        final var bsn = new int[]{1, 2, 3};
        when(randomDigitsSupplier.supply()).thenReturn(bsn);
        when(numberElevenProof.test(bsn)).thenReturn(Boolean.TRUE);
        when(intArrayToString.apply(bsn)).thenReturn("123");

        final var generatedResult = generator.generate();

        assertThat(generatedResult).isEqualTo("123");
        verify(randomDigitsSupplier).supply();
        verify(numberElevenProof).test(bsn);
        verify(intArrayToString).apply(bsn);
    }
}