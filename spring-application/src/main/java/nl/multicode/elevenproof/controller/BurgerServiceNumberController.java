package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.BurgerServiceNumber;
import nl.multicode.elevenproof.service.BurgerServiceNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/bsn")
@RestController
@RequiredArgsConstructor
public class BurgerServiceNumberController implements ElevenproofController<ResponseEntity<String>> {

  private final BurgerServiceNumberService service;

  @GetMapping("/generate")
  public ResponseEntity<String> generate() {

    return ResponseEntity.ok(service.generate().getNumber());
  }

  @GetMapping("/validate/{number}")
  public ResponseEntity<String> validate(@PathVariable String number) {

    return ResponseEntity.ok(service.validate(BurgerServiceNumber.builder().number(number).build()));
  }
}
