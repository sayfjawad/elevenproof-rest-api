package nl.multicode.elevenproof.controller;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/bank")
@RestController
@RequiredArgsConstructor
public class BankNumberController implements ElevenproofController<ResponseEntity<String>> {

  private final BankAccountNumberService service;

  @GetMapping("/generate")
  public ResponseEntity<String> generate() {

    return ResponseEntity.ok(service.generate().number());
  }

  @GetMapping("/validate/{number}")
  public ResponseEntity<String> validate(@PathVariable String number) {

    // BankAccountNumber number = new BankAccountNumber();

    return ResponseEntity.ok(service.validate(BankAccountNumberDto.builder().number(number).build()));
  }
}
