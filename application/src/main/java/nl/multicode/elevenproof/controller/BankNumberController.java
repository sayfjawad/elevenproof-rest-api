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

        final var accountNumber = new BankAccountNumber();
        accountNumber.setNumber(service.generate().number());
        return ResponseEntity.ok(accountNumber);
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BankAccountNumber> validate(@PathVariable("number") String number) {

        final var accountNumber = new BankAccountNumber();
        accountNumber.setNumber(number);
        accountNumber.setIsElevenproof(service.isValid(number));
        return ResponseEntity.ok(accountNumber);
    }
}
