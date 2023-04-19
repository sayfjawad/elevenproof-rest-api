//package nl.multicode.elevenproof.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatusCode;
//
//@ExtendWith(MockitoExtension.class)
//class BankAccountNumberDtoControllerTest {
//
//
//    @InjectMocks
//    BankNumberController controller;
//
//
//    @Test
//    @DisplayName("Given a request to generate is passed to the controller\n"
//            + "Then the service is called to generate a new eleven proof number\n"
//            + "And the controller returns the generated number\n")
//    void testGetBsn_bsn_generate_is_called() {
//
//        assertThat(controller.generate().getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
//    }
//
//    @Test
//    @DisplayName("Given a request to validate is passed to the controller\n"
//            + "Then the service is called to validate the proof number\n"
//            + "And the controller returns the validated response for that number\n")
//    void testValidateBankAccountNumber_number_validation() {
//
//        final var bsn = "123456789";
//
//        controller.validate(bsn).getBody();
//
//    }
//}