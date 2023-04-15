package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.model.BankAccountNumber;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class BankAccountNumberControllerTest {

  @Mock
  BankAccountNumberService service;

  @InjectMocks
  BankNumberController controller;


  @Test
  void testGetBsn_bsn_generate_is_called() {

    when(service.generate()).thenReturn(mock(BankAccountNumber.class));

    assertThat(controller.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    verify(service).generate();
  }

  @Test
  void testValidateBankAccountNumber_number_validation() {

    final var bsn = "123456789";
    when(service.validate(any(BankAccountNumber.class))).thenReturn("result");

    controller.validate(bsn).getBody();

    verify(service).validate(any(BankAccountNumber.class));
  }
}