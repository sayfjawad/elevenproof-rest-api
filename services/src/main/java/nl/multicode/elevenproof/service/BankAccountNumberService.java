package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;

@RequiredArgsConstructor
public class BankAccountNumberService implements ElevenProofService<BankAccountNumberDto> {

    private final BankAccountNumberGenerator generator;

    private final BankAccountNumberElevenProof elevenProof;

    private final StringToIntArray stringToIntArray;

    @Override
    public BankAccountNumberDto generate() {

        return BankAccountNumberDto.builder()
                .number(generator.generate())
                .build();
    }

    @Override
    public boolean isValid(String number) {

        final var bsnDigits = stringToIntArray.apply(number);
        return elevenProof.test(bsnDigits);
    }
}
