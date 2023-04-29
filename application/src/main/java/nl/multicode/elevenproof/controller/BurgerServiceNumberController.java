package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.service.ElevenproofBeforeBigBang;
import nl.multicode.elevenproof.openapi.model.BurgerServiceNumber;
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

    private final ElevenproofBeforeBigBang elevenproofBeforeBigBang;

    @GetMapping("/generate")
    public ResponseEntity<BurgerServiceNumber> generate() {

        return ResponseEntity.ok(BurgerServiceNumber.builder()
                .number(elevenproofBeforeBigBang.executeCommand(
                        new String[]{"generate", "bank"}).getNumber())
                .build());
    }

    @GetMapping("/validate/{number}")
    public ResponseEntity<BurgerServiceNumber> validate(@PathVariable("number") String number) {

        return ResponseEntity.ok(BurgerServiceNumber.builder()
                .number(number)
                .isElevenproof(elevenproofBeforeBigBang.executeCommand(
                        new String[]{"validate", "bank", number}).isValid())
                .build());
    }
}
