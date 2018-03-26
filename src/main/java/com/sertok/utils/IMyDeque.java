package com.sertok.utils;

public interface IMyDeque<T> {
    void add(T i);
    T get(int i);
    T getFirst();
    T getLast();
    void removeLast();
    int size();
}
