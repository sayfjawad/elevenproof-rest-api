package nl.multicode.elevenproof;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.spring.SpringFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        objectFactory = SpringFactory.class,
        plugin = {"pretty"},
        features = "src/test/resources/features",
        monochrome = true
)
@CucumberContextConfiguration
@SpringBootTest(classes = {Application.class})
public class RunCucumberTest {

}
