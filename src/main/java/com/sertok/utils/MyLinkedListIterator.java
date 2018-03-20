package com.sertok.utils;

import java.util.Iterator;

import static com.sertok.utils.MyLinkedList.INIT_CAPACITY;


public class MyLinkedListIterator<T> implements Iterator<T> {
    private Node<T> first;
    private int index = -1;

    public MyLinkedListIterator(Node<T> first) {
        this.first = first;
    }

    @Override
    public boolean hasNext() {
        index++;
        return first != null;
    }

    @Override
    public T next() {
        T value;
        int index = this.index % INIT_CAPACITY;
        value= first.value[index];
        if(index==INIT_CAPACITY-1||this.first.value[index+1]==null)
            first=first.next;
        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
