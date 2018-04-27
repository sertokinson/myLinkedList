package com.stakhanov;

import com.sertok.utils.MyDeque;
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
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(arrayList.add(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddLinkedList(Blackhole blackhole) {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(linkedList.add(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddDeque(Blackhole blackhole) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(deque.add(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testAddMyDeque(Blackhole blackhole) {
        MyDeque<Integer> myDeque = new MyDeque<>();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(myDeque.add(i));
        }
    }
}
