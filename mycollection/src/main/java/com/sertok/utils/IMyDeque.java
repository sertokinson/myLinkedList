package com.sertok.utils;

import java.util.Iterator;

public interface IMyDeque<T>  {
    boolean add(T i);
    T get(int i);
    T getFirst();
    T getLast();
    void removeLast();
    int size();
    Iterator<T> iterator();
}
