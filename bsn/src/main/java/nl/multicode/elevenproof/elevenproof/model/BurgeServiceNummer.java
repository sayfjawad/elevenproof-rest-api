package nl.multicode.elevenproof.elevenproof.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.multicode.elevenproof.elevenproof.validation.BsnConstraint;

@Getter
@AllArgsConstructor
public class BurgeServiceNummer {

    @BsnConstraint()
    private final String nummer;

}