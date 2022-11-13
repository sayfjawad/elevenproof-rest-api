package nl.multicode.rest;

import lombok.RequiredArgsConstructor;
import nl.multicode.bsn.service.BsnService;
import nl.multicode.elevenproof.model.ElevenProofNumber;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/bsn")
public class BsnController {

  private final BsnService bsnService;

  @GetMapping("/validate/{bsn}")
  public Boolean validateBsn(@PathVariable String bsn) {

    return bsnService.isValidBsn(new ElevenProofNumber(bsn));
  }

  @GetMapping("/generate")
  public String generateBsn() {

    return bsnService.generateBsn();
  }
}

