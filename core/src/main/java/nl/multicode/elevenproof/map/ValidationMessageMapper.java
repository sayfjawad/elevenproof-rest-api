package nl.multicode.elevenproof.map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidationMessageMapper {

  public static final String IS = "is";
  public static final String IS_NOT = "is not";

  public String getMessage(String number, boolean isValid) {

    final var isElevenProof = isValid ? IS : IS_NOT;
    return "number[" + number + "] " + isElevenProof + " eleven proof!";
  }
}
