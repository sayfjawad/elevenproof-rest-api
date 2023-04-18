package nl.multicode.elevenproof.map;

import java.util.function.Function;

public class StringToIntArray implements Function<String, int[]> {

    @Override
    public int[] apply(String number) {

        return number.chars()
                .map(Character::getNumericValue)
                .toArray();
    }
}
