package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.proof.BsnElevenProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class BurgerServiceNumberControllerTest {

  @Mock
  BurgerServiceNummerGenerator generator;
  @Mock
  BsnElevenProof validator;
  @Mock
  StringToIntArray stringToIntArray;

  @InjectMocks
  BurgerServiceNumberController burgerServiceNumberController;

  @Test
  void testGetBsn_bsn_generator_is_called() {

    assertThat(burgerServiceNumberController.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    verify(generator).generate();
  }

  @Test
  void testValidateBsn_bsn_validation_valid_message() {

    final var bsn = "123456789";
    final var bsnInts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    when(stringToIntArray.apply(bsn)).thenReturn(bsnInts);
    when(validator.test(bsnInts)).thenReturn(Boolean.TRUE);

    final var result = burgerServiceNumberController.validate(bsn).getBody();

    verify(stringToIntArray).apply(bsn);
    verify(validator).test(bsnInts);
    assertThat(result).isEqualTo("number[123456789] is eleven proof!");
  }

  @Test
  void testValidateBsn_bsn_validation_invalid_message() {

    final var bsn = "123456789";
    final var bsnInts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    when(stringToIntArray.apply(bsn)).thenReturn(bsnInts);
    when(validator.test(bsnInts)).thenReturn(Boolean.FALSE);

    final var result = burgerServiceNumberController.validate(bsn).getBody();

    verify(stringToIntArray).apply(bsn);
    verify(validator).test(bsnInts);
    assertThat(result).isEqualTo("number[123456789] is not eleven proof!");
  }

}