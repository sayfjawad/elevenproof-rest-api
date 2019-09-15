package nl.multicode.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.multicode.bsn.validation.BsnConstraint;

@Getter
@AllArgsConstructor
public class BsnForm {

    @BsnConstraint
    String bsn;
}
