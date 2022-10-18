package nl.multicode.bsn;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void main_args() {
        App.main(new String[]{"validate", "218996755"});
        assertThat(outContent.toString()).isEqualTo("218996755 is valid bsn\n");
    }

    @Test
    public void main_no_args() throws ArrayIndexOutOfBoundsException {
        App.main(null);
        assertThat(outContent.toString().trim().length()).isEqualTo(19);
        assertThat(outContent.toString().trim().matches("Optional\\[[0-9]{9}\\]")).isTrue();
    }
}
