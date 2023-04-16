package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.service.BurgerServiceNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class BurgerServiceNumberControllerTest {

  @Mock
  BurgerServiceNumberService service;

  @InjectMocks
  BurgerServiceNumberController controller;

  @Test
  void testGetBsn_bsn_generator_is_called() {

    when(service.generate()).thenReturn(mock(BurgerServiceNumberDto.class));

    assertThat(controller.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    verify(service).generate();
  }

  @Test
  void testValidateBsn_bsn_validation_valid_message() {

    final var bsn = "123456789";
    when(service.validate(any(BurgerServiceNumberDto.class))).thenReturn("result");

    controller.validate(bsn).getBody();

    verify(service).validate(any(BurgerServiceNumberDto.class));
  }
}