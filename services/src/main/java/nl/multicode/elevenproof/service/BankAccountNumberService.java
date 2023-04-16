package nl.multicode.elevenproof.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.map.ValidationMessageMapper;
import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;

@RequiredArgsConstructor
public class BankAccountNumberService implements ElevenProofService<BankAccountNumberDto> {

  private final BankAccountNumberGenerator generator;
  private final BankAccountNumberElevenProof elevenProof;
  private final StringToIntArray stringToIntArray;
  private final ValidationMessageMapper validationMessageMapper;

  @Override
  public BankAccountNumberDto generate() {

    return BankAccountNumberDto.builder()
        .number(generator.generate())
        .build();
  }

  @Override
  public String validate(BankAccountNumberDto number) {

    final var bsnDigits = stringToIntArray.apply(number.number());
    final var isElevenProof = elevenProof.test(bsnDigits);

    return validationMessageMapper.getMessage(
        Objects.requireNonNull(number).number(), isElevenProof);
  }
}
