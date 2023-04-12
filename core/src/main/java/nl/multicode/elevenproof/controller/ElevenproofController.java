package nl.multicode.elevenproof.controller;

import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.ElevenProof;

public interface ElevenproofController<T> {

  T generate();

  T validate(String number);

  default String getMessage(String number, StringToIntArray stringToIntArray, ElevenProof elevenProof) {

    final var bsnDigits = stringToIntArray.apply(number);
    final var isElevenProof = elevenProof.test(bsnDigits) ? "is" : "is not";
    return "number[" + number + "] " + isElevenProof + " eleven proof!";
  }

}
