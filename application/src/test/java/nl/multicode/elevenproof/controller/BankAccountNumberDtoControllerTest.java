package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class BankAccountNumberDtoControllerTest {

    @Mock
    BankAccountNumberService service;

    @InjectMocks
    BankNumberController controller;


    @Test
    @DisplayName("Given a request to generate is passed to the controller\n"
            + "Then the service is called to generate a new eleven proof number\n"
            + "And the controller returns the generated number\n")
    void testGetBsn_bsn_generate_is_called() {

        when(service.generate()).thenReturn(mock(BankAccountNumberDto.class));

        assertThat(controller.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        verify(service).generate();
    }

    @Test
    @DisplayName("Given a request to validate is passed to the controller\n"
            + "Then the service is called to validate the proof number\n"
            + "And the controller returns the validated response for that number\n")
    void testValidateBankAccountNumber_number_validation() {

        final var bsn = "123456789";
        when(service.isValid(bsn)).thenReturn(true);

        controller.validate(bsn).getBody();

        verify(service).isValid(bsn);
    }
}