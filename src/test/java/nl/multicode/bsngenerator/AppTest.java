package nl.multicode.bsngenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    public void main_args() {
        app.main(new String[]{"2"});
        assertEquals(20, outContent.toString().length());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void main_no_args() throws ArrayIndexOutOfBoundsException {
        app.main(new String[]{});
        fail();
    }
}
