package nl.multicode.elevenproof.config;

import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.validate.proof.BankAccountNumberElevenProof;
import nl.multicode.elevenproof.validate.proof.BsnElevenProof;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


  @Bean
  public BankAccountNumberGenerator getBankAccountNumberGenerator(IntArrayToString intArrayToString,
      BankAccountNumberElevenProof bankAccountNumberElevenProof) {

    return new BankAccountNumberGenerator(new FixedLengthStringRandomNumbersSupplier(BurgerServiceNummerGenerator.BSN_DIGITS_LENGTH),
        intArrayToString,
        bankAccountNumberElevenProof);
  }

  @Bean
  public BurgerServiceNummerGenerator getBurgerServiceNummerGenerator(IntArrayToString intArrayToString, BsnElevenProof bsnElevenProof) {

    return new BurgerServiceNummerGenerator(new FixedLengthStringRandomNumbersSupplier(BurgerServiceNummerGenerator.BSN_DIGITS_LENGTH),
        intArrayToString,
        bsnElevenProof);
  }

  @Bean
  public BsnElevenProof getBsnElevenProof() {

    return new BsnElevenProof();
  }

  @Bean
  public BankAccountNumberElevenProof getBankAccountNumberElevenProof() {

    return new BankAccountNumberElevenProof();
  }

  @Bean
  public IntArrayToString getIntArrayToString() {

    return new IntArrayToString();
  }
  @Bean
  public StringToIntArray getStringToIntArray() {

    return new StringToIntArray();
  }
}
