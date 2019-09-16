package nl.multicode.rest;

import lombok.AllArgsConstructor;
import nl.multicode.bsn.model.BurgeServiceNummer;
import nl.multicode.bsn.service.BsnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/bsn")
public class BsnController {

    private final BsnService bsnService;

    @GetMapping("/validate/{bsn}")
    public Boolean validateBsn(@PathVariable String bsn) {
        return bsnService.isValidBsn(new BurgeServiceNummer(bsn));
    }

    @GetMapping("/generate")
    public String generateBsn() {
        return bsnService.generateBsn();
    }
}

