package me.alidg;

import java.util.concurrent.ThreadLocalRandom;

public class CompactCounter implements Counter {

    private final long[] bits;

    public CompactCounter(int numberOfBits) {
        bits = new long[(int) Math.ceil(numberOfBits / 64.0)];
    }

    @Override
    public long count() {
        long sum = 0;
        for (long bit : bits) {
            sum += Long.bitCount(bit);
        }

        return sum;
    }

    public static CompactCounter randomCompactCounter(int length) {
        CompactCounter counter = new CompactCounter(length);
        for (int i = 0; i < length; i++) {
            int wordIndex = i / 64;
            if (ThreadLocalRandom.current().nextBoolean())
                counter.bits[wordIndex] |= (1L << i);
        }

        return counter;
    }
}
