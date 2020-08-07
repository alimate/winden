package me.alidg;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class WindenBenchmark {

    @Param("10000000")
    int length;

    private final BooleanCounter randomBooleanCounter = BooleanCounter.randomFlags(length);
    private final BooleanCounter sortedBooleanCounter = BooleanCounter.halfSetHalfClear(length);
    private final ByteCounter bitwiseCounter = ByteCounter.randomFlags(length);

    @Benchmark
    public long randomBoolean() {
        return randomBooleanCounter.count();
    }

    @Benchmark
    public long sortedBoolean() {
        return sortedBooleanCounter.count();
    }

    @Benchmark
    public long bitwiseCounter() {
        return bitwiseCounter.count();
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
