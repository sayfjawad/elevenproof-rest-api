package nl.multicode.elevenproof.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Command {
    GENERATE("generate"), VALIDATE("validate"), UNKNOWN("unknown");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command fromValue(final String value) {
        return Arrays.stream(Command.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(Command.UNKNOWN);
    }
}
