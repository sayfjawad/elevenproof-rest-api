package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.proof.BankAccountNumberElevenProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class BankAccountNumberControllerTest {

  @Mock
  BankAccountNumberGenerator generator;
  @Mock
  BankAccountNumberElevenProof validator;
  @Mock
  StringToIntArray stringToIntArray;

  @InjectMocks
  BankNumberController controller;

  @Test
  void testGetBsn_bsn_generator_is_called() {

    assertThat(controller.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    verify(generator).generate();
  }

  @Test
  void testValidateBsn_bsn_validation_valid_message() {

    final var bank = "1234567890";
    final var bankInts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    when(stringToIntArray.apply(bank)).thenReturn(bankInts);
    when(validator.test(bankInts)).thenReturn(Boolean.TRUE);
    final var result = controller.validate(bank).getBody();

    verify(stringToIntArray).apply(bank);
    verify(validator).test(bankInts);
    assertThat(result).isEqualTo("number[1234567890] is eleven proof!");
  }

  @Test
  void testValidateBsn_bsn_validation_invalid_message() {

    final var bsn = "123456789";
    final var bsnInts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    when(stringToIntArray.apply(bsn)).thenReturn(bsnInts);
    when(validator.test(bsnInts)).thenReturn(Boolean.FALSE);

    final var result = controller.validate(bsn).getBody();

    verify(stringToIntArray).apply(bsn);
    verify(validator).test(bsnInts);
    assertThat(result).isEqualTo("number[123456789] is not eleven proof!");
  }
}