package nl.multicode.elevenproof.glue.stepdefinition;

import static io.restassured.RestAssured.given;
import static nl.multicode.elevenproof.driver.util.Constants.ELEVENPROOF_REST_API_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import nl.multicode.elevenproof.openapi.model.BankAccountNumber;
import nl.multicode.elevenproof.openapi.model.BurgerServiceNumber;
import org.apache.http.HttpStatus;


public class StepDefinitions {

    private Map<String, Object> context;

    private ObjectMapper objectMapper;

    @Before
    public void setup() {

        context = new HashMap<>();
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() {

        context = null;
    }

    @Given("the application is up and running")
    public void anExampleScenario() {

        final var response = given().get(
                ELEVENPROOF_REST_API_ENDPOINT.resolve("/api/swagger-ui/index.html"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @When("^endpoint (.*) is called$")
    public void callEndpointStepDefinition(String endpoint) {

        final var response = given().get(ELEVENPROOF_REST_API_ENDPOINT.resolve(endpoint));
        context.put("response", response);
    }

    @Then("^status of response is expected to be equal to (.*)$")
    public void statusOfResponseStepDefinition(int status) {

        final var response = (Response) context.get("response");
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @SneakyThrows
    @Then("^generated (.*) is expected to be valid$")
    public void generatedResponseIsValidStepDefinition(String elevenproof) {

        final var response = (Response) context.get("response");

        switch (elevenproof) {
            case "BSN": {
                final var number = objectMapper.readValue(response.getBody().asByteArray(),
                        BurgerServiceNumber.class);
                assertThat(number.getNumber()).hasSize(9);
                break;
            }
            case "BANK": {
                final var number = objectMapper.readValue(response.getBody().asByteArray(),
                        BankAccountNumber.class);
                assertThat(number.getNumber()).hasSize(10);
                break;
            }
            default:
                fail("Eleven proof must be of either BSN or BANK type!");
        }
    }

    @SneakyThrows
    @Then("^validated (.*) is expected to be (.*)$")
    public void validatedResponseIsValidStepDefinition(String elevenproof, boolean valid) {

        final var response = (Response) context.get("response");

        switch (elevenproof) {
            case "BSN": {
                final var number = objectMapper.readValue(response.getBody().asByteArray(),
                        BurgerServiceNumber.class);
                assertThat(number.getIsElevenproof()).isEqualTo(valid);
                break;
            }
            case "BANK": {
                final var number = objectMapper.readValue(response.getBody().asByteArray(),
                        BankAccountNumber.class);
                assertThat(number.getIsElevenproof()).isEqualTo(valid);
                break;
            }
            default:
                fail("Eleven proof must be of either BSN or BANK type!");
        }
    }
}
