package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.map.ValidationMessageMapper;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.validate.BurgerServiceNumberProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BurgerServiceNumberServiceTest {


  @Mock
  private BurgerServiceNummerGenerator generator;
  @Mock
  private BurgerServiceNumberProof elevenProof;
  @Mock
  private StringToIntArray stringToIntArray;
  @Mock
  private ValidationMessageMapper validationMessageMapper;

  @InjectMocks
  private BurgerServiceNumberService service;

  @Test
  void generate() {

    final var number = "number";
    when(generator.generate()).thenReturn(number);

    final var result = service.generate();

    assertThat(result.number()).isEqualTo(number);
    verify(generator).generate();

  }

  @Test
  void validate() {

    final var bsnIntegers = new int[]{};
    final var number = "number";
    final var message = "message";
    final var validationResult = true;
    when(stringToIntArray.apply(number)).thenReturn(bsnIntegers);
    when(elevenProof.test(bsnIntegers)).thenReturn(validationResult);
    when(validationMessageMapper.getMessage(number, validationResult)).thenReturn(message);

    final var result = service.validate(BurgerServiceNumberDto.builder().number(number).build());

    assertThat(result).isEqualTo(message);
    verify(stringToIntArray).apply(number);
    verify(elevenProof).test(bsnIntegers);
    verify(validationMessageMapper).getMessage(number, validationResult);
  }
}