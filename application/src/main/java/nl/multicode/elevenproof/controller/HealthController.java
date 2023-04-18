package nl.multicode.elevenproof.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/appinfo/health/liveness")
@RestController
public class HealthController {

    @GetMapping
    public Health getHealth() {

        return Health.up().build();
    }
}
