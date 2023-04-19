package nl.multicode.elevenproof.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElevenproofBeforeBigBangTest {

    ElevenproofBeforeBigBang elevenproof;
    
    @BeforeEach
    public void setup(){
        elevenproof = new ElevenproofBeforeBigBang();
    }

    @Test
    void executeCommand_valid_bsn() {
        final var result = elevenproof.executeCommand(new String[]{"validate", "bsn", "218996755"});
        assertThat(result.isValid()).isTrue();
    }

    @Test
    void executeCommand_valid_bank() {
        final var result = elevenproof.executeCommand(new String[]{"validate", "bank", "1015008577"});
        assertThat(result.isValid()).isTrue();
    }

    @Test
    void executeCommand_invalid_bsn() {
        final var result = elevenproof.executeCommand(new String[]{"validate", "bsn", "228996755"});
        assertThat(result.isValid()).isFalse();
    }

    @Test
    void executeCommand_invalid_bank() {
        final var result = elevenproof.executeCommand(new String[]{"validate", "bank", "1115008577"});
        assertThat(result.isValid()).isFalse();
    }

    @Test
    void executeCommand_generate_bsn() {
        final var result = elevenproof.executeCommand(new String[]{"generate", "bsn"});
        assertThat(result.getNumber()).matches("^[0-9]{9}$");
    }  @Test
    void executeCommand_generate_bank() {
        final var result = elevenproof.executeCommand(new String[]{"generate", "bank"});
        assertThat(result.getNumber()).matches("^[0-9]{10}$");
    }

}