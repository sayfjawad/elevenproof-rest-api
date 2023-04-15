package nl.multicode.elevenproof.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.map.ValidationMessageMapper;
import nl.multicode.elevenproof.model.BankAccountNumber;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;

@RequiredArgsConstructor
public class BankAccountNumberService implements ElevenProofService<BankAccountNumber> {

  private final BankAccountNumberGenerator generator;
  private final BankAccountNumberElevenProof elevenProof;
  private final StringToIntArray stringToIntArray;
  private final ValidationMessageMapper validationMessageMapper;

  @Override
  public BankAccountNumber generate() {

    return BankAccountNumber.builder()
        .number(generator.generate())
        .build();
  }

  @Override
  public String validate(BankAccountNumber number) {

    final var bsnDigits = stringToIntArray.apply(number.getNumber());
    final var isElevenProof = elevenProof.test(bsnDigits);

    return validationMessageMapper.getMessage(
        Objects.requireNonNull(number).getNumber(), isElevenProof);
  }
}
