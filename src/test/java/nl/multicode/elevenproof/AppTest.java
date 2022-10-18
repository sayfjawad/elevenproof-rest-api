package nl.multicode.elevenproof;


import nl.multicode.elevenproof.model.Command;
import nl.multicode.elevenproof.model.ProofType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void main_args_validate_bsn() {
        App.main(new String[]{Command.VALIDATE.getValue(), ProofType.BSN.getValue(), "218996755"});
        assertThat(outContent.toString()).isEqualTo("218996755 is valid bsn\n");
    }

    @Test
    public void main_args_generate_bsn() throws ArrayIndexOutOfBoundsException {
        App.main(new String[]{Command.GENERATE.getValue(), ProofType.BSN.getValue()});
        assertThat(outContent.toString().trim().length()).isEqualTo(19);
        assertThat(outContent.toString().trim().matches("Optional\\[[0-9]{9}\\]")).isTrue();
    }
}
