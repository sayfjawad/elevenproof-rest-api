package nl.multicode.elevenproof.map;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IntArrayToString implements Function<int[], String> {

    @Override
    public String apply(int[] ints) {

        return Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
