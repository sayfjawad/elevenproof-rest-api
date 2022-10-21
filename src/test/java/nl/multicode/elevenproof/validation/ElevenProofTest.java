package nl.multicode.elevenproof.validation;


import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ElevenProofTest {

    private ElevenProof elevenProof;

    @Test
    public void multipliers() {
        elevenProof = createElfproefImpl(new int[]{1, 2});
        assertTrue(elevenProof.isElevenProof("15"));
        assertFalse(elevenProof.isElevenProof("16"));
    }

    private ElevenProof createElfproefImpl(int[] multipliers) {
        return new ElevenProof() {
            @Override
            public boolean isElevenProof(String nummer) {
                return isElevenProof(nummer, multipliers);
            }
        };
    }
}
