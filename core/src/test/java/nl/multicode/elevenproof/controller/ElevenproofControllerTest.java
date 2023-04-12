package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.ElevenProof;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElevenproofControllerTest {

  @Test
  void getMessage_isElevenProof() {

    final var dummyNumber = "dummyNumber";
    final var elevenProof = mock(ElevenProof.class);
    final var stringToIntArray = mock(StringToIntArray.class);
    final var digits = new int[]{};

    when(stringToIntArray.apply(dummyNumber)).thenReturn(digits);
    when(elevenProof.test(digits)).thenReturn(Boolean.TRUE);

    assertThat(getElevenproofController().getMessage(dummyNumber, stringToIntArray, elevenProof))
        .isEqualTo("number[dummyNumber] is eleven proof!");
  }

  @Test
  void getMessage_isNotElevenProof() {

    final var dummyNumber = "dummyNumber";
    final var elevenProof = mock(ElevenProof.class);
    final var stringToIntArray = mock(StringToIntArray.class);
    final var digits = new int[]{};

    when(stringToIntArray.apply(dummyNumber)).thenReturn(digits);
    when(elevenProof.test(digits)).thenReturn(Boolean.FALSE);

    assertThat(getElevenproofController().getMessage(dummyNumber, stringToIntArray, elevenProof))
        .isEqualTo("number[dummyNumber] is not eleven proof!");
  }

  private ElevenproofController<String> getElevenproofController(){

    return new ElevenproofController<String>() {
      @Override
      public String generate() {

        return null;
      }

      @Override
      public String validate(String number) {

        return null;
      }
    };
  }
}