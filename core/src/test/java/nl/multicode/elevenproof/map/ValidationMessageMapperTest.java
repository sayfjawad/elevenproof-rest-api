package nl.multicode.elevenproof.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationMessageMapperTest {

  ValidationMessageMapper mapper;

  @BeforeEach
  public void setup() {

    mapper = new ValidationMessageMapper();
  }

  @Test
  void getMessage_valid() {

    final var result = mapper.getMessage("number", true);
    assertThat(result).isEqualTo("number[number] is eleven proof!");
  }

  @Test
  void getMessage_invalid() {

    final var result = mapper.getMessage("number", false);
    assertThat(result).isEqualTo("number[number] is not eleven proof!");
  }
}