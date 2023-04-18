package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;

class HealthControllerTest {

    @Test
    @DisplayName("Given the application is working \n"
            + "When the endpoint is called \n"
            + "Then the controller returns Status.UP\n")
    void getHealth() {

        final var result = new HealthController().getHealth();
        assertThat(result.getStatus()).isEqualTo(Status.UP);
    }
}