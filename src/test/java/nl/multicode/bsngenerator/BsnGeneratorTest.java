package nl.multicode.bsngenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BsnGeneratorTest {

    public static final int EXPECTED_LENGTH = 9;
    public static final int EXPECTED_RESULTS_NUMBER = 10;

    private BsnGenerator bsnGenerator;

    @Before
    public void setup(){
        bsnGenerator = new BsnGenerator();
    }

    @Test
    public void generateBsn() {
        assertNotNull(bsnGenerator.generateBsn());
        assertEquals(EXPECTED_LENGTH, bsnGenerator.generateBsn().length());
    }

    @Test
    public void generateBsnNummers() {
        Set<String> generatedBsnSet = bsnGenerator.generateRandomBsnNummers(10);
        assertNotNull(generatedBsnSet);
        assertEquals(EXPECTED_RESULTS_NUMBER, generatedBsnSet.size());
    }
}
