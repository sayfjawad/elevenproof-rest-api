package nl.multicode.elevenproof.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.map.ValidationMessageMapper;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.validate.BurgerServiceNumberProof;

@RequiredArgsConstructor
public class BurgerServiceNumberService implements ElevenProofService<BurgerServiceNumberDto> {

  private final BurgerServiceNummerGenerator generator;
  private final BurgerServiceNumberProof elevenProof;
  private final StringToIntArray stringToIntArray;
  private final ValidationMessageMapper validationMessageMapper;

  @Override
  public BurgerServiceNumberDto generate() {

    return BurgerServiceNumberDto.builder()
        .number(generator.generate())
        .build();
  }

  @Override
  public String validate(BurgerServiceNumberDto number) {

    final var bsnDigits = stringToIntArray.apply(number.number());
    final var isElevenProof = elevenProof.test(bsnDigits);

    return validationMessageMapper.getMessage(
        Objects.requireNonNull(number).number(), isElevenProof);
  }
}
