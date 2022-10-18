package nl.multicode.elevenproof.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProofType {
    BSN("bsn"), BANK_ACCOUNT("bank"), UNKNOWN("unknown");

    private final String value;

    ProofType(String value) {
        this.value = value;
    }

    public static ProofType fromValue(final String value) {
        return Arrays.stream(ProofType.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(ProofType.UNKNOWN);
    }
}
