package me.alidg;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class WindenBenchmark {

    @Param("1")
    int length;

    private List<Long> sorted;
    private List<Long> shuffled;

    @Setup(Level.Trial)
    public void setup() {
        sorted = LongStream.range(0, length).boxed().collect(toList());

        shuffled = LongStream.range(0, length).boxed().collect(toList());
        Collections.shuffle(shuffled);
    }

    @Benchmark
    public long sorted() {
        return sorted.stream().filter(v -> v < length / 2).count();
    }

    @Benchmark
    public long shuffled() {
        return shuffled.stream().filter(v -> v < length / 2).count();
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
