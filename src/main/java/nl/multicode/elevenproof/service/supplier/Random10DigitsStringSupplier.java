package nl.multicode.elevenproof.service.supplier;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Random10DigitsStringSupplier implements ObjectSupplier<String> {

    @Override
    public String supply() {
        return IntStream.rangeClosed(0, 9).boxed()
                .map(r -> Integer.toString(new Random().nextInt(10)))
                .collect(Collectors.joining());
    }
}