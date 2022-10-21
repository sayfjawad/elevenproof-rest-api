package nl.multicode.elevenproof;


import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import nl.multicode.elevenproof.util.TestAppender;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @BeforeEach
    public void setup() {
        TestAppender.clear();
    }

    @AfterEach
    public void tearDown() {
        TestAppender.clear();
    }

    @Test
    void main_args_validate_bsn() {
        App.main(new String[]{Command.VALIDATE.getValue(), ProofType.BSN.getValue(), "218996755"});
        assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("218996755 is valid bsn");

    }

    @Test
    void main_args_generate_bsn() throws ArrayIndexOutOfBoundsException {
        App.main(new String[]{Command.GENERATE.getValue(), ProofType.BSN.getValue()});
        assertThat(TestAppender.getLogs(Level.INFO).get(0)).matches("Generated: [0-9]{9}");
    }

    @Test
    void main_args_invalid_command_argument() throws ArrayIndexOutOfBoundsException {
        App.main(new String[]{"wrong command", "bsn"});
        assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("Usage is:\n" +
                "java -jar app.jar <validate> <bsn|bank> <number>\n" +
                "java -jar app.jar <generate> <bsn|bank>");
    }

    @Test
    void main_args_invalid_prooftype_argument() throws ArrayIndexOutOfBoundsException {
        App.main(new String[]{"generate", "wrong proofType"});
        assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("Usage is:\n" +
                "java -jar app.jar <validate> <bsn|bank> <number>\n" +
                "java -jar app.jar <generate> <bsn|bank>");
    }

    @Test
    void main_args_missing_arguments() throws ArrayIndexOutOfBoundsException {
        App.main(new String[]{"validate", "bsn"});
        assertThat(TestAppender.getLogs(Level.INFO).get(0)).isEqualTo("Usage is:\n" +
                "java -jar app.jar <validate> <bsn|bank> <number>\n" +
                "java -jar app.jar <generate> <bsn|bank>");
    }
}
