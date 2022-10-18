package nl.multicode.elevenproof.model;

import nl.multicode.elevenproof.model.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommandTest {

    @DisplayName("Given the string value of the Enum when passed to fromValue method"
            + " Then it should convert string value to corresponding enum")
    @ParameterizedTest
    @EnumSource(Command.class)
    void fromValue_canCovertAllEnumValues(Command accumulationType) {
        assertThat(accumulationType).isEqualTo(Command.fromValue(accumulationType.getValue()));
    }
}