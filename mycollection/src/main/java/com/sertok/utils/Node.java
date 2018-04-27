package com.sertok.utils;

public class Node<T> {
    Node prev;
    Node next;
    T[] value;

    public Node(Node prev, Node next, T[] value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}
