package nl.multicode.elevenproof;

import nl.multicode.elevenproof.util.TestAppender;
import org.apache.logging.log4j.Level;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AppTest {

  @BeforeEach
  public void setup() {

    TestAppender.clear();
  }

  @AfterEach
  public void tearDown() {

    TestAppender.clear();
  }

  @ParameterizedTest
  @CsvSource({"validate,bsn,000342865", "validate,bank,0609567128"})
  @DisplayName("Given all arguments are given"
      + "And the arguments are valid"
      + "And the command is VALIDATE"
      + "And the BSN/BANK is valid"
      + "When the application is run with these arguments"
      + "Then output will confirm the BSN/BANK is valid")
  void main_args_validate_bsn(String command, String proofType, String number) {

    App.main(new String[]{command, proofType, number});
    Assertions.assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo(number + " is valid " + proofType);

  }

  @ParameterizedTest
  @CsvSource({"generate,bsn,[0-9]{9}", "generate,bank,[0-9]{10}"})
  @DisplayName("Given all arguments are given"
      + "And the arguments are valid"
      + "And the command is GENERATE"
      + "When the application is run with these arguments"
      + "Then output will generate a valid elevenproof number of the given type")
  void main_args_generate_bsn(String command, String proofType, String pattern) {

    App.main(new String[]{command, proofType});
    Assertions.assertThat(TestAppender.getLogs(Level.INFO).get(0)).matches("Generated: Optional\\[" + pattern + "\\]");
  }

  @ParameterizedTest
  @CsvSource({"wrong,wrong,wrong",
      "generate,wrong,wrong",
      "validate,wrong,wrong",
      "wrong,bsn,wrong",
      "wrong,bank,wrong"})
  @DisplayName("Given one or more arguments is/are invalid"
      + "When the application is run with these arguments"
      + "Then output will be of the correct usage of the application")
  void main_args_invalid_command_argument(String command, String proofType, String number) throws ArrayIndexOutOfBoundsException {

    App.main(new String[]{command, proofType, number});
    Assertions.assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("Usage is:\n" +
        "java -jar app.jar <validate> <bsn|bank> <number>\n" +
        "java -jar app.jar <generate> <bsn|bank>");
  }
}
