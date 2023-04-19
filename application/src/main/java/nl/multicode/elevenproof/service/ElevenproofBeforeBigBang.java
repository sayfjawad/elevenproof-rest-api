package nl.multicode.elevenproof.service;

import nl.multicode.elevenproof.model.BankAccountNumberDto;
import nl.multicode.elevenproof.model.BurgerServiceNumberDto;
import nl.multicode.elevenproof.model.ElevenproofNumebr;

public class ElevenproofBeforeBigBang {

    /**
     * This is a multipurpose eleven proof method that can either generate or validate either bank
     * or bsn number depending on the input
     *
     * @param args is a String array
     *             where arg[0] is command <generate || validate>
     *             and   arg[1] is a type <bsn || bank>
     *             and   arg[2] is a number to be validated
     * @return ElevenproofNumber object with a result.
     */
    public ElevenproofNumebr executeCommand(String[] args) {

        if (args[0] != null && args[1] != null) {
            if ("validate".equals(args[0]) && args[2] != null && (args[2].length() == 9
                    || args[2].length() == 10)) {
                boolean result = false;
                boolean finished = false;

                int[] m = new int[0];
                int j = 0;
                if ("bsn".equals(args[1]) && args[2].length() == 9) {
                    j = 9;
                    m = new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1};
                } else if ("bank".equals(args[1]) && args[2].length() == 10) {
                    j = 10;
                    m = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                } else {
                    finished = true;
                }
                if (!finished) {
                    int sum = 0;
                    for (int i = 0; i < j; i++) {
                        sum += Character.getNumericValue(args[2].toCharArray()[i]) * m[i];
                    }
                    result = (sum % 11 == 0);
                }

                if (result) {
                    if (args[1].equals("bsn")) {
                        return BurgerServiceNumberDto.builder()
                                .number(args[2])
                                .valid(true)
                                .build();
                    } else if (args[1].equals("bank")) {
                        return BankAccountNumberDto.builder()
                                .number(args[2])
                                .valid(true)
                                .build();
                    }

                } else {
                    if (args[1].equals("bsn")) {
                        return BurgerServiceNumberDto.builder()
                                .number(args[2])
                                .valid(false)
                                .build();
                    } else if (args[1].equals("bank")) {
                        return BankAccountNumberDto.builder()
                                .number(args[2])
                                .valid(false)
                                .build();
                    }
                }
            } else if ("generate".equals(args[0])) {
                if ("bsn".equals(args[1])) {
                    int l = 9;
                    while (true) {

                        String n = "";
                        for (int i = 0; i < l; i++) {
                            n += (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                        }
                        String randomNumber = n;
                        boolean result = false;
                        boolean finished = false;
                        String[] args1 = new String[]{"validate", "bsn", randomNumber};

                        int[] m = new int[0];
                        int j = 0;
                        if ("bsn".equals(args1[1]) && args1[2].length() == 9) {
                            j = 9;
                            m = new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1};
                        } else if ("bank".equals(args1[1]) && args1[2].length() == 10) {
                            j = 10;
                            m = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                        } else {
                            finished = true;
                        }
                        if (!finished) {
                            int sum = 0;
                            for (int i = 0; i < j; i++) {
                                sum += Character.getNumericValue(args1[2].toCharArray()[i]) * m[i];
                            }
                            result = (sum % 11 == 0);
                        }

                        if (result) {
                            return BurgerServiceNumberDto.builder().number(randomNumber).build();
                        }
                    }
                } else if ("bank".equals(args[1])) {
                    int l = 10;
                    while (true) {

                        String n = "";
                        for (int i = 0; i < l; i++) {
                            n += (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                        }
                        String randomNumber = n;
                        boolean result = false;
                        boolean finished = false;
                        String[] args1 = new String[]{"validate", "bank", randomNumber};

                        int[] m = new int[0];
                        int j = 0;
                        if ("bsn".equals(args1[1]) && args1[2].length() == 9) {
                            j = 9;
                            m = new int[]{9, 8, 7, 6, 5, 4, 3, 2, -1};
                        } else if ("bank".equals(args1[1]) && args1[2].length() == 10) {
                            j = 10;
                            m = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                        } else {
                            finished = true;
                        }
                        if (!finished) {
                            int sum = 0;
                            for (int i = 0; i < j; i++) {
                                sum += Character.getNumericValue(args1[2].toCharArray()[i]) * m[i];
                            }
                            result = (sum % 11 == 0);
                        }

                        if (result) {
                            return BankAccountNumberDto.builder().number(randomNumber).build();
                        }
                    }
                }
            }
        }
        return null;
    }
}
