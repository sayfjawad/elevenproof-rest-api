package nl.multicode.elevenproof.generator.supplier;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomFixedLengthDigitsSupplier implements ObjectSupplier<String> {

  public static final int MAX_RANDOM_INTEGER = 9;
  private static final Random random = new Random();
  private final int digitsLength;


  @Override
  public String supply() {

    int startInclusive = 0;
    int endInclusive = digitsLength - 1;

    return IntStream.rangeClosed(startInclusive, endInclusive).boxed()
        .map(r -> Integer.toString(random.nextInt(MAX_RANDOM_INTEGER)))
        .collect(Collectors.joining());
  }
}