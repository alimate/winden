package me.alidg;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class WindenBenchmark {

    @Param("1")
    int length;

    private BooleanCounter randomBooleanCounter;
    private BooleanCounter sortedBooleanCounter;
    private ByteCounter bitwiseCounter;
    private CompactCounter compactCounter;

    @Setup(Level.Trial)
    public void setup() {
        randomBooleanCounter = BooleanCounter.randomFlags(length);
        sortedBooleanCounter = BooleanCounter.partitionedFlags(length);
        bitwiseCounter = ByteCounter.randomFlags(length);
        compactCounter = CompactCounter.randomFlags(length);
    }

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

    @Benchmark
    public long compactCounter() {
        return compactCounter.count();
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
