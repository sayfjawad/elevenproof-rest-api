package nl.multicode.elevenproof.glue.stepdefinition;

import static io.restassured.RestAssured.given;
import static nl.multicode.elevenproof.driver.util.Constants.ELEVENPROOF_REST_API_ENDPOINT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;


public class StepDefinitions {

    @Given("the application is up and running")
    public void anExampleScenario() {

        final var response = given().get(
                ELEVENPROOF_REST_API_ENDPOINT.resolve("/api/swagger-ui/index.html"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @When("^endpoint (.*) is called status (.*) is expected$")
    public void CallEndpointAndExpectResultStepDefinition(String endpoint, int expectedStatus) {

        final var response = given().get(ELEVENPROOF_REST_API_ENDPOINT.resolve(endpoint));
        assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }

    @Then("all endpoints are available")
    public void theScenarioPasses() {

    }

}
