package nl.multicode.elevenproof.glue.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Gegeven;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import nl.multicode.elevenproof.driver.util.Constants;

public class EndpointsAvailabilityStepDefinitions {


  @Gegeven("een correcte werking van de applicatie en beschikbaarheid van gegevens voor de endpoints:")
  public void setup() {
    // bereid de applicatie voor
  }

  @Als("het endpoint {string} met POST wordt aangeroepen")
  public void endpointPost(String uri) {

    final var response = given().post(Constants.ELEVENPROOF_REST_API_ENDPOINT.resolve(uri));
    final var stateMap = new HashMap<String, Object>();
    stateMap.put(uri, response);
  }

  @Als("het endpoint {string} met GET en ACCEPT {string} wordt aangeroepen")
  public void endpointGet(String uri, String accept) {

    final var response = given().accept(accept).get(Constants.ELEVENPROOF_REST_API_ENDPOINT.resolve(uri));
    final var stateMap = new HashMap<String, Object>();
    stateMap.put(uri, response);
  }

  @Als("het endpoint {string} met GET en ACCEPT {string} wordt aangeroepen met date query parameter")
  public void endpointGetWithDate(String uri, String accept) {

    final var response = given().accept(accept).get(Constants.ELEVENPROOF_REST_API_ENDPOINT.resolve(
        uri + "&date=" + OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE)));
    final var stateMap = new HashMap<String, Object>();
    stateMap.put(uri, response);
  }

  @Als("de endpoint {string} met POST wordt aangeroepen dan geeft het een {int} terug")
  public void doPost(String uri, int status) {

    final var response = given().post(Constants.ELEVENPROOF_REST_API_ENDPOINT.resolve(uri));
    assertThat(response.getStatusCode()).isEqualTo(status);
  }

  @Als("de endpoint {string} met GET wordt aangeroepen dan geeft het een {int} terug")
  public void doGet(String uri, int status) {

    final var response = given().get(Constants.ELEVENPROOF_REST_API_ENDPOINT.resolve(uri));
    assertThat(response.getStatusCode()).isEqualTo(status);
  }
}
