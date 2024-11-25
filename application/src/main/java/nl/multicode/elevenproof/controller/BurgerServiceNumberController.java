package nl.multicode.elevenproof.controller;

import jakarta.validation.constraints.Pattern;
import javax.validation.Valid;
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
            @Pattern(regexp = "\\d{8,9}", message = "The number must be exactly 8 or 9 digits")
            @PathVariable("number") String number) {

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
}
