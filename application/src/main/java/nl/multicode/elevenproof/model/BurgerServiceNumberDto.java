package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder
public record BurgerServiceNumberDto(String number, Boolean valid) implements ElevenproofNumebr {

    @Override
    public String getNumber() {

        return number;
    }

    @Override
    public Boolean isValid() {

        return valid;
    }
}
