package me.alidg;

import java.util.concurrent.ThreadLocalRandom;

public class BooleanCounter implements Counter {

    private final boolean[] flags;

    public BooleanCounter(int numberOfBits) {
        flags = new boolean[numberOfBits];
    }

    @Override
    public long count() {
        long sum = 0;
        for (boolean flag : flags) {
            if (flag) sum++;
        }

        return sum;
    }

    public static BooleanCounter randomFlags(int length) {
        BooleanCounter counter = new BooleanCounter(length);
        for (int i = 0; i < length; i++) {
            counter.flags[i] = ThreadLocalRandom.current().nextBoolean();
        }

        return counter;
    }

    public static BooleanCounter partitionedFlags(int length) {
        BooleanCounter counter = new BooleanCounter(length);
        for (int i = 0; i < 3 * length / 4; i++) {
            counter.flags[i] = true;
        }

        return counter;
    }
}
