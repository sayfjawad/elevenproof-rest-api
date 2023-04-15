package nl.multicode.elevenproof.model;

import lombok.Builder;

@Builder

public class BankAccountNumber implements ElevenproofNumebr {

  private final String number;

  @Override
  public String getNumber() {

    return number;
  }
}
