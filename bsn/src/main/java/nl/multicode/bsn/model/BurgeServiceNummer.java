package nl.multicode.bsn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.multicode.bsn.validation.BsnConstraint;

@Getter
@AllArgsConstructor
public class BurgeServiceNummer {

    @BsnConstraint()
    private final String nummer;

}