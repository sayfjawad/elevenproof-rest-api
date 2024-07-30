package nl.multicode.elevenproof.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BurgerServiceNumberController implements
        ElevenproofController<ResponseEntity<BurgerServiceNumber>> {

    private final ElevenProofService<BurgerServiceNumberDto> service;

    @GetMapping("/generate")
    public ResponseEntity<BurgerServiceNumber> generate() {

        BurgerServiceNumber serviceNumber = new BurgerServiceNumber();
        serviceNumber.setNumber(service.generate().number());
        return ResponseEntity.ok(serviceNumber);
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BurgerServiceNumber> validate(
            @Valid @PathVariable("number") String number) {

        BurgerServiceNumber serviceNumber = new BurgerServiceNumber();
        serviceNumber.setNumber(number);
        serviceNumber.setIsElevenproof(service.isValid(number));

        return ResponseEntity.ok(serviceNumber);
    }
}
