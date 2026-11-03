package nl.multicode.elevenproof.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import nl.multicode.elevenproof.generate.BankAccountNumberGenerator;
import nl.multicode.elevenproof.generate.CitizenServiceNumberGenerator;
import nl.multicode.elevenproof.generate.supplier.FixedLengthStringRandomNumbersSupplier;
import nl.multicode.elevenproof.map.IntArrayToString;
import nl.multicode.elevenproof.map.StringToIntArray;
import nl.multicode.elevenproof.service.BankAccountNumberService;
import nl.multicode.elevenproof.service.CitizenServiceNumberService;
import nl.multicode.elevenproof.validate.BankAccountNumberElevenProof;
import nl.multicode.elevenproof.validate.CitizenServiceNumberElevenProof;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final var openapi = new OpenAPI();
        openapi.addServersItem(new Server().url("https://eleven.multicode.nl"));
        openapi.addServersItem(new Server().url("http://localhost:8080"));
        return openapi;
    }

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
    public CitizenServiceNumberGenerator getCitizenServiceNumberGenerator(
            IntArrayToString intArrayToString,
            CitizenServiceNumberElevenProof citizenServiceNumberElevenProof) {

        return new CitizenServiceNumberGenerator(
                new FixedLengthStringRandomNumbersSupplier(
                        CitizenServiceNumberGenerator.BSN_DIGITS_LENGTH),
                intArrayToString,
                citizenServiceNumberElevenProof);
    }

    @Bean
    public CitizenServiceNumberElevenProof getCitizenServiceNumberElevenProof() {

        return new CitizenServiceNumberElevenProof();
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
    public CitizenServiceNumberService getCitizenServiceNumberService(
            CitizenServiceNumberGenerator generator,
            CitizenServiceNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new CitizenServiceNumberService(generator, elevenProof, stringToIntArray);
    }

    @Bean
    public BankAccountNumberService getBankAccountNumberService(
            BankAccountNumberGenerator generator,
            BankAccountNumberElevenProof elevenProof,
            StringToIntArray stringToIntArray) {

        return new BankAccountNumberService(generator, elevenProof, stringToIntArray);
    }
}
