package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.ElevenproofController;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.proof.BsnElevenProof;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/bsn")
@RestController
@RequiredArgsConstructor
public class BurgerServiceNumberController implements ElevenproofController<ResponseEntity<String>> {

  private final BurgerServiceNummerGenerator bsnGenerator;
  private final BsnElevenProof elevenProof;
  private final StringToIntArray stringToIntArray;


  @GetMapping("/validate/{bsn}")
  public ResponseEntity<String> validate(@PathVariable String bsn) {

    final var bsnDigits = stringToIntArray.apply(bsn);
    final var isElevenProof = elevenProof.test(bsnDigits) ? "is" : "is not";
    final var message = "number[" + bsn + "] " + isElevenProof + " eleven proof!";
    return ResponseEntity.ok(message);
  }

  @GetMapping("/generate")
  public ResponseEntity<String> generate() {

    return ResponseEntity.ok(bsnGenerator.generate().toString());
  }
}
