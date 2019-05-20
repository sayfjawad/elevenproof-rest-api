package nl.multicode.bsngenerator;

import nl.multicode.bsngenerator.elfproef.ElfProef;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BsnGenerator {
    private static final int MAX = 999_999_998;
    private static final int MIN = 100_000_000;

    private ElfProef elfProef;

    public BsnGenerator(){
        elfProef = new ElfProef();
    }

    public String generateBsn(){
        return generateRandomBsnNummers(1).iterator().next();
    }

    public Set<String> generateRandomBsnNummers(int aantal){
        Set<String> bsnSet = new HashSet<>();

        while(bsnSet.size() < aantal){
            long randomNumber = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);

            if(elfProef.isGeldigBSN(randomNumber)){
                bsnSet.add(String.valueOf(randomNumber));
            }
        }

        return bsnSet;
    }
}
