package com.stakhanov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestGetLinkedList {

    private static class SetLinkedList {
        List<Integer> list = new LinkedList<>();
        SetLinkedList() {
            for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
                list.add(i);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class MyState {
        SetLinkedList linkedList;

        @Setup(Level.Invocation)
        public void setUp() {
            linkedList = new SetLinkedList();
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getFirst(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.linkedList.list.get(0));
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getLast(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.linkedList.list.get(Constant.COUNT_ELEMENT-1));
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getMiddle(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.linkedList.list.get(i/2));
        }
    }

}
