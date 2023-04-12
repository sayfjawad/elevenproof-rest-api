package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
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


  @GetMapping("/generate")
  public ResponseEntity<String> generate() {

    return ResponseEntity.ok(bsnGenerator.generate().toString());
  }

  @GetMapping("/validate/{bsn}")
  public ResponseEntity<String> validate(@PathVariable String bsn) {

    return ResponseEntity.ok(getMessage(bsn, stringToIntArray, elevenProof));
  }
}
