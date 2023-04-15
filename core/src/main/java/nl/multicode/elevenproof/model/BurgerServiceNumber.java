package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder

public class BurgerServiceNumber implements ElevenproofNumebr {

  private final String number;

  @Override
  public String getNumber() {

    return number;
  }
}
