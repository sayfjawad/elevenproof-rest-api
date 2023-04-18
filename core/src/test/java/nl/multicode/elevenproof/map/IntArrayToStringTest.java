package nl.multicode.elevenproof.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IntArrayToStringTest {

    IntArrayToString intArrayToString;

    private static int[] charArrayToIntArray(char[] charArray) {

        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = Character.getNumericValue(charArray[i]);
        }
        return intArray;
    }

    @BeforeEach
    public void setup() {

        intArrayToString = new IntArrayToString();
    }

    @ParameterizedTest
    @CsvSource({"001199", "0123456", "91234"})
    void apply(String string) {

        assertThat(intArrayToString.apply(charArrayToIntArray(string.toCharArray()))).isEqualTo(
                string);
    }
}