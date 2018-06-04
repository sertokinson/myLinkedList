package com.stakhanov;

import com.sertok.utils.MyDeque;
import org.ahmadsoft.ropes.Rope;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestAdd {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddArrayList(Blackhole blackhole) {
        List<Rope> arrayList = new ArrayList<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(arrayList.add(Rope.BUILDER.build("")));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddLinkedList(Blackhole blackhole) {
        List<Rope> linkedList = new LinkedList<>();
        if (Constant.COUNT_ELEMENT < 10000000)
            for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
                blackhole.consume(linkedList.add(Rope.BUILDER.build("")));
            }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddDeque(Blackhole blackhole) {
        Deque<Rope> deque = new ArrayDeque<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(deque.add(Rope.BUILDER.build("")));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddMyDeque(Blackhole blackhole) {
        MyDeque<Rope> myDeque = new MyDeque<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(myDeque.add(Rope.BUILDER.build("")));
        }
    }
}
