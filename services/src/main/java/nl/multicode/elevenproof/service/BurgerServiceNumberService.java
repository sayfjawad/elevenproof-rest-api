package nl.multicode.elevenproof.service;

import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.validate.BurgerServiceNumberProof;

@RequiredArgsConstructor
public class BurgerServiceNumberService implements ElevenProofService<BurgerServiceNumberDto> {

    private final BurgerServiceNummerGenerator generator;

    private final BurgerServiceNumberProof elevenProof;

    private final StringToIntArray stringToIntArray;

    @Override
    public BurgerServiceNumberDto generate() {

        return BurgerServiceNumberDto.builder()
                .number(generator.generate())
                .build();
    }

    @Override
    public boolean isValid(String number) {

        final var bsnDigits = stringToIntArray.apply(number);
        return elevenProof.test(bsnDigits);
    }
}
