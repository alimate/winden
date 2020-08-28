package me.alidg;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class WindenBenchmark {

    @Param("1")
    int length;

    private long[] sorted;
    private long[] shuffled;

    @Setup
    public void setup() {
        sorted = LongStream.range(0, length).toArray();
        shuffled = LongStream.range(0, length).toArray();
        for (int i = 0; i < length; i++) {
            int from = ThreadLocalRandom.current().nextInt(0, length);
            int to = ThreadLocalRandom.current().nextInt(0, length);

            long temp = shuffled[from];
            shuffled[from] = shuffled[to];
            shuffled[to] = temp;
        }

        int misplaced = 0;
        for (int i = 0; i < length; i++) {
            if (shuffled[i] != i) misplaced++;
        }

        System.out.println("Randomness: " + (float) misplaced * 100 / length);
    }

    @Benchmark
    public long sorted() {
        return Arrays.stream(sorted).filter(v -> v < length / 2).count();
    }

    @Benchmark
    public long shuffled() {
        return Arrays.stream(shuffled).filter(v -> v < length / 2).count();
    }

    @Benchmark
    public long sortedMove() {
        return Arrays.stream(sorted).map(v -> toggle(v - length / 2)).sum();
    }


    @Benchmark
    public long shuffledMove() {
        return Arrays.stream(shuffled).map(v -> toggle(v - length / 2)).sum();
    }

    private long toggle(long x) {
        return x >>> 63;
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
