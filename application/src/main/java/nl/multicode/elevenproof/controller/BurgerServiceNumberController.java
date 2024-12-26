package nl.multicode.elevenproof.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.openapi.model.BurgerServiceNumber;
import nl.multicode.elevenproof.service.ElevenProofService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/bsn")
@RestController
public class BurgerServiceNumberController implements
        ElevenproofController<ResponseEntity<BurgerServiceNumber>> {

    public static final String ZERO_PREFIX = "0";
    private final ElevenProofService<BurgerServiceNumberDto> service;

    public BurgerServiceNumberController(ElevenProofService<BurgerServiceNumberDto> service) {

        this.service = service;
    }

    @GetMapping("/generate")
    public ResponseEntity<BurgerServiceNumber> generate() {

        return ResponseEntity.ok(
                BurgerServiceNumber.builder().number(service.generate().number()).build());
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BurgerServiceNumber> validate(
            @Valid
            @PathVariable("number") String number) {

        this.validateNumber(number);
        final String nineDigitNumber = ensureNineDigits(number);
        return ResponseEntity.ok(
                BurgerServiceNumber.builder()
                        .number(nineDigitNumber)
                        .isElevenproof(service.isValid(nineDigitNumber))
                        .build());
    }

    private static String ensureNineDigits(final String number) {

        return number.length() == 9 ? number : ZERO_PREFIX + number;
    }

    private void validateNumber(final String number) {

        if (!number.matches("\\d{8,9}")) {
            throw new IllegalArgumentException("The number must be exactly 8 or 9 digits");
        }
    }
}
