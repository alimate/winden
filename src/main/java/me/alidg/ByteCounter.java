package me.alidg;

import java.util.concurrent.ThreadLocalRandom;

public class ByteCounter implements Counter {

    private final byte[] flags;

    public ByteCounter(int numberOfBits) {
        flags = new byte[numberOfBits];
    }

    @Override
    public long count() {
        long sum = 0;
        for (byte flag : flags) {
            sum += flag;
        }

        return sum;
    }

    public static ByteCounter randomFlags(int length) {
        ByteCounter counter = new ByteCounter(length);
        for (int i = 0; i < length; i++) {
            counter.flags[i] = (byte) ThreadLocalRandom.current().nextInt(2);
        }

        return counter;
    }
}
