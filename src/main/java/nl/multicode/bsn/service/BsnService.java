package nl.multicode.bsn.service;

import nl.multicode.bsn.validation.BsnElfproef;
import nl.multicode.bsn.validation.ElfProef;

import java.util.concurrent.ThreadLocalRandom;

public class BsnService {
    private static final int MAX = 999_999_999;
    private static final int MIN = 100_000_000;
    private final ElfProef elfProef;

    public BsnService() {
        elfProef = new BsnElfproef();
    }

    public BsnService(ElfProef elfProef) {
        this.elfProef = elfProef;
    }

    public String generateRandomBsnNummers() {
        while (true) {
            long randomNumber = ThreadLocalRandom.current().nextInt(MIN, MAX);
            String randomBsn = Long.toString(randomNumber);
            if (elfProef.isElfproef(randomBsn)) {
                return randomBsn;
            }
        }
    }

    public Boolean isValidBsn(String bsn) {
        return elfProef.isElfproef(bsn);
    }
}
