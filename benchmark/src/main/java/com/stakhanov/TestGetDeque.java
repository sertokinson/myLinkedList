package com.stakhanov;

 import com.stakhanov.Constant;
 import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestGetDeque {

    private static class SetDeque {
        Deque<Integer> deque =new ArrayDeque<>();
        SetDeque() {
            for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
                deque.add(i);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class MyState {
        SetDeque deque;

        @Setup(Level.Invocation)
        public void setUp() {
            deque = new SetDeque();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getFirst(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.deque.deque.getFirst());
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getLast(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.deque.deque.getLast());
        }
    }

}
