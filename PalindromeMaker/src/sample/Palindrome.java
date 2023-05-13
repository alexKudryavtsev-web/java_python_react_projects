package sample;

public final class Palindrome {

    public long[] create(Long start){
        if(start <= 0)
            throw new IllegalArgumentException(
                    "start is negate or equals 0");
        if(palindrome(start))
            return new long[]{start, 0};
        int quantity = 0;

        Long res = start;
        while (true){
            res += reserve(res);
            quantity++;
            if(palindrome(res))
                return new long[]{res, quantity};
        }
    }

    private boolean palindrome(Long num){
        String str = num.toString();
        return str.equals(
                new StringBuffer(str).
                        reverse().
                        toString());

    }

    private long reserve(Long num){
        if(num < 10)
            return num;
        return Integer.parseInt(
                new StringBuilder(
                        num.toString()).
                        reverse().
                        toString());
    }
}