package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.openapi.model.BankAccountNumber;
import nl.multicode.elevenproof.service.ElevenProofService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/bank")
@RestController
@RequiredArgsConstructor
public class BankNumberController implements
        ElevenproofController<ResponseEntity<BankAccountNumber>> {

    private final ElevenProofService<BankAccountNumberDto> service;

    @GetMapping("/generate")
    public ResponseEntity<BankAccountNumber> generate() {

        return ResponseEntity.ok(BankAccountNumber.builder()
                .number(service.generate().number())
                .build());
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BankAccountNumber> validate(@PathVariable("number") String number) {

        return ResponseEntity.ok(BankAccountNumber.builder()
                .number(number)
                .isElevenproof(service.isValid(number))
                .build());
    }
}
