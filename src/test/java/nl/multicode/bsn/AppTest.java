package nl.multicode.bsn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void main_args() {
        App.main(new String[]{"validate", "218996755"});
        assertEquals("218996755 is valid bsn\n", outContent.toString());
    }

    @Test
    public void main_no_args() throws ArrayIndexOutOfBoundsException {
        App.main(null);
        assertEquals(9, outContent.toString().trim().length());
        assertTrue(outContent.toString().trim().matches("[0-9]{9}"));
    }
}
