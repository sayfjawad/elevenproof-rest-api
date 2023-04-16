package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder
public record BurgerServiceNumberDto(String number) implements ElevenproofNumebr {

}
