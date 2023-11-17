package nl.multicode.elevenproof.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.service.ElevenProofService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(BurgerServiceNumberController.class)
class BurgerServiceNumberControllerRestTest {

    @MockBean
    private ElevenProofService<BurgerServiceNumberDto> service;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void testGenerate() {

        final var mockResponse = new BurgerServiceNumberDto("123456789");
        when(service.generate()).thenReturn(mockResponse);

        RestAssuredMockMvc.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/bsn/generate")
                .then()
                .statusCode(200)
                .body("number", equalTo("123456789"));
    }

    @Test
    void testValidate() {

        String testNumber = "987654321";
        when(service.isValid(testNumber)).thenReturn(true);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/bsn/validate/{number}", testNumber)
                .then()
                .statusCode(200)
                .body("number", equalTo(testNumber))
                .body("isElevenproof", equalTo(true));
    }
}
