package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.Generator;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.validate.ElevenProof;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankAccountElevenProofServiceTest {

    @Mock
    private Generator elevenproofGenerator;
    @Mock
    private ElevenProof elevenProof;
    @InjectMocks
    private BankAccountElevenProofService service;

    @Test
    @DisplayName("Given the Service is configured correctly"
        + "When the generate() method is called"
        + "Then the BangAccountGenerator member is called with the correct elevenProofType")
    void generate() {

        service.generate();
        verify(elevenproofGenerator).generate(ProofType.BANK_ACCOUNT);
    }

    @ParameterizedTest
    @CsvSource({"1,true", "2,false"})
    @DisplayName("Given the Service is configured correctly"
        + "When the isValid() method is called"
        + "Then the elevenProof is called with the number")
    void isValid(int number, boolean outcome) {

        final var bankAccount = new int[]{number};
        when(elevenProof.isElevenProof(bankAccount)).thenReturn(outcome);

        assertThat(service.isValid(bankAccount)).isEqualTo(outcome);
        verify(elevenProof).isElevenProof(bankAccount);
    }
}