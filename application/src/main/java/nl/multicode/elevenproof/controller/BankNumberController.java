package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.ElevenproofBeforeBigBang;
import nl.multicode.elevenproof.openapi.model.BankAccountNumber;
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

    private final ElevenproofBeforeBigBang elevenproofBeforeBigBang;

    @GetMapping("/generate")
    public ResponseEntity<BankAccountNumber> generate() {

        return ResponseEntity.ok(BankAccountNumber.builder()
                .number(elevenproofBeforeBigBang.executeCommand(
                        new String[]{"generate", "bank"}).getNumber())
                .build());
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BankAccountNumber> validate(@PathVariable("number") String number) {


        return ResponseEntity.ok(BankAccountNumber.builder()
                .number(number)
                .isElevenproof(elevenproofBeforeBigBang.executeCommand(
                        new String[]{"validate", "bank", number}).isValid())
                .build());
    }
}
