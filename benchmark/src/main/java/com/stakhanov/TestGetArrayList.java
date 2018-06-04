package com.stakhanov;

import org.ahmadsoft.ropes.Rope;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestGetArrayList {

    private static class SetArrayList {
        List<Rope> list = new ArrayList<>();
        SetArrayList() {
            for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
                list.add(Rope.BUILDER.build(""));
            }
        }
    }


    @State(Scope.Benchmark)
    public static class MyState {
        SetArrayList arrayList;

        @Setup(Level.Invocation)
        public void setUp() {
            arrayList = new SetArrayList();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getFirst(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.arrayList.list.get(0));
        }
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getMiddle(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.arrayList.list.get(i/2));
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getLast(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.arrayList.list.get(Constant.COUNT_ELEMENT-1));
        }
    }

}
