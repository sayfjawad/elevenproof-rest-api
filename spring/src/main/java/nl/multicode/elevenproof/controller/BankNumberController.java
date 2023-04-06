package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.ElevenproofController;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.proof.BankAccountNumberElevenProof;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/bank")
@RestController
@RequiredArgsConstructor
public class BankNumberController implements ElevenproofController<ResponseEntity<String>> {

  private final BankAccountNumberGenerator bankAccountNumberGenerator;
  private final BankAccountNumberElevenProof elevenProof;
  private final StringToIntArray stringToIntArray;


  @GetMapping("/generate")
  public ResponseEntity<String> generate() {

    return ResponseEntity.ok(bankAccountNumberGenerator.generate().orElse(null));
  }


  @GetMapping("/validate/{number}")
  public ResponseEntity<String> validate(@PathVariable String number) {

    final var bsnDigits = stringToIntArray.apply(number);
    final var isElevenProof = elevenProof.test(bsnDigits) ? "is" : "is not";
    final var message = "number[" + number + "] " + isElevenProof + " eleven proof!";
    return ResponseEntity.ok(message);
  }
}
