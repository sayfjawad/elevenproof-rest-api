package nl.multicode.bsngenerator.elfproef;

public final class ElfProef
{
    private static final long[] BSN_ONDNR_MULTIPLIERS = {9, 8, 7, 6, 5, 4, 3, 2, -1};

    public boolean isGeldigBSN(String bsn)
    {
        try
        {
            Long.valueOf(bsn);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return isElfProef(getDigits(bsn), BSN_ONDNR_MULTIPLIERS);
    }

    public boolean isGeldigBSN(Long bsn)
    {
        return isGeldigBSN(bsn.toString());
    }

    private boolean isElfProef(long[] digits, long[] multipliers)
    {
        return isElfProef(digits, multipliers, 0);
    }

    private boolean isElfProef(long[] digits, long[] multipliers, long uitkomst)
    {
        long sum = 0;
        for (int i = 0; i < multipliers.length; i++)
        {
            sum += digits[i] * multipliers[i];
        }
        return sum % 11 == uitkomst;
    }

    private long[] getDigits(String nummer)
    {
        long[] digits = new long[9];

        for (int i = nummer.length() - 1, j = 1; i >= 0; i--, j++)
        {
            digits[digits.length - j] = Character.getNumericValue(nummer.charAt(i));
        }
        return digits;
    }
}
