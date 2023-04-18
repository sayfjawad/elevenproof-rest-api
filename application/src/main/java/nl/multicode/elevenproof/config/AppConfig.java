package nl.multicode.elevenproof.config;

import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.generate.BurgerServiceNummerGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import nl.multicode.elevenproof.service.BurgerServiceNumberService;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;
import nl.multicode.elevenproof.validate.BurgerServiceNumberProof;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public BankAccountNumberGenerator getBankAccountNumberGenerator(
            IntArrayToString intArrayToString,
            BankAccountNumberElevenProof bankAccountNumberElevenProof) {

        return new BankAccountNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        BankAccountNumberGenerator.BANK_ACCOUNT_DIGITS_LENGTH),
                intArrayToString,
                bankAccountNumberElevenProof);
    }

    @Bean
    public BurgerServiceNummerGenerator getBurgerServiceNummerGenerator(
            IntArrayToString intArrayToString,
            BurgerServiceNumberProof burgerServiceNumberProof) {

        return new BurgerServiceNummerGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        BurgerServiceNummerGenerator.BSN_DIGITS_LENGTH),
                intArrayToString,
                burgerServiceNumberProof);
    }

    @Bean
    public BurgerServiceNumberProof getBsnElevenProof() {

        return new BurgerServiceNumberProof();
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

    @Bean
    public BurgerServiceNumberService getBurgerServiceNumberService(
            BurgerServiceNummerGenerator generator,
            BurgerServiceNumberProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new BurgerServiceNumberService(generator, elevenProof, stringToIntArray);
    }

    @Bean
    public BankAccountNumberService getBankAccountNumberService(
            BankAccountNumberGenerator generator,
            BankAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new BankAccountNumberService(generator, elevenProof, stringToIntArray);
    }
}
