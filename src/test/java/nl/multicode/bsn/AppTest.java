package nl.multicode.bsn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private App app;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        app = new App();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void main_null_args() {
        app.main(null);
        assertEquals(10, outContent.toString().length());
    }

    @Test
    public void main_no_args() throws ArrayIndexOutOfBoundsException {
        app.main(new String[]{});
        assertEquals(10, outContent.toString().length());
    }
}
