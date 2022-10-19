package nl.multicode.elevenproof.service.supplier;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class RandomDigitsStringSupplier implements ObjectSupplier<String> {

    private final int digitsLength;

    @Override
    public String supply() {
        return IntStream.rangeClosed(0, digitsLength - 1).boxed()
                .map(r -> Integer.toString(new Random().nextInt(9)))
                .collect(Collectors.joining());
    }
}