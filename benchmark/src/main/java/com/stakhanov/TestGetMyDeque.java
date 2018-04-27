/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.stakhanov;

import com.sertok.utils.MyDeque;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

public class TestGetMyDeque {

    private static class SetDeque {
        MyDeque<Integer> deque = new MyDeque<>();

        SetDeque() {
            for (int i = 0; i <Constant.COUNT_ELEMENT; i++) {
                deque.add(i);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class MyState{
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
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void getMiddle(MyState state, Blackhole blackhole) {
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            blackhole.consume(state.deque.deque.get(i/2));
        }
    }

}
