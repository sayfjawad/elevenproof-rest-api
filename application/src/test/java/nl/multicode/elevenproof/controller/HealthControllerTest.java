package nl.multicode.elevenproof.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;

class HealthControllerTest {

  @Test
  void getHealth() {

    final var result = new HealthController().getHealth();
    assertThat(result.getStatus()).isEqualTo(Status.UP);
  }
}