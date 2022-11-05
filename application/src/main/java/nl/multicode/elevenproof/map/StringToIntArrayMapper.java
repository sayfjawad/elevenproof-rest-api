package nl.multicode.elevenproof.map;

public class StringToIntArrayMapper implements CustomMapper<String, int[]> {

  @Override
  public int[] map(String number) {

    return number.chars()
        .map(Character::getNumericValue)
        .toArray();
  }
}
