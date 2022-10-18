package nl.multicode.elevenproof.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProofTypeTest {

    @DisplayName("Given the string value of the Enum when passed to fromValue method"
            + " Then it should convert string value to corresponding enum")
    @ParameterizedTest
    @EnumSource(ProofType.class)
    void fromValue_canCovertAllEnumValues(ProofType proofType) {
        assertThat(proofType).isEqualTo(ProofType.fromValue(proofType.getValue()));
    }
}