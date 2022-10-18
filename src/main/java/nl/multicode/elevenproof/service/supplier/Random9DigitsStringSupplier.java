package nl.multicode.elevenproof.service.supplier;

import java.util.concurrent.ThreadLocalRandom;

public class Random9DigitsStringSupplier implements ObjectSupplier<String> {

    private static final int MAX = 999_999_999;
    private static final int MIN = 100_000_000;

    @Override
    public String supply() {
        return Integer.toString(ThreadLocalRandom.current().nextInt(MIN, MAX));
    }
}
