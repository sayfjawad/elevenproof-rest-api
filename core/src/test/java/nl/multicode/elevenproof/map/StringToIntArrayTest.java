package nl.multicode.elevenproof.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToIntArrayTest {

    StringToIntArray stringToIntArray;

    @BeforeEach
    public void setup() {

        stringToIntArray = new StringToIntArray();
    }

    @ParameterizedTest
    @CsvSource({"001199", "0123456", "91234"})
    void apply(String string) {

        final var digitStringOrigin = string.split("");
        final var digitIntResult = stringToIntArray.apply(string);

        for (int index = 0; index < string.length(); index++) {
            assertThat(Integer.parseInt(digitStringOrigin[index])).isEqualTo(digitIntResult[index]);
        }
    }
}