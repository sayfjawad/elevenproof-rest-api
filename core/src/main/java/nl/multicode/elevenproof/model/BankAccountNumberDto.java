package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder
public record BankAccountNumberDto(String number) implements ElevenproofNumebr {

}
