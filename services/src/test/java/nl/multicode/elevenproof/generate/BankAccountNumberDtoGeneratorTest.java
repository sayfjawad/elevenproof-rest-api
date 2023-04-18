package nl.multicode.elevenproof.generate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.supplier.ObjectSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.validate.ElevenProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankAccountNumberDtoGeneratorTest {


    @Mock
    private ObjectSupplier<int[]> randomDigitsSupplier;

    @Mock
    private IntArrayToString intArrayToString;

    @Mock
    private ElevenProof numberElevenProof;

    @InjectMocks
    private BankAccountNumberGenerator generator;

    @Test
    void generate() {

        final var bank = new int[]{1, 2, 3};
        when(randomDigitsSupplier.supply()).thenReturn(bank);
        when(numberElevenProof.test(bank)).thenReturn(Boolean.TRUE);
        when(intArrayToString.apply(bank)).thenReturn("123");

        final var generatedResult = generator.generate();

        assertThat(generatedResult).isEqualTo("123");
        verify(randomDigitsSupplier).supply();
        verify(numberElevenProof).test(bank);
        verify(intArrayToString).apply(bank);
    }
}