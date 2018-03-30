import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class TestDeque {

    public static void main(String[] args) {
        Deque deque = new ArrayDeque();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            deque.add(i);
        }

        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            deque.getLast();
        }
        long finish=System.currentTimeMillis();
        System.out.println("deque: "+(finish-start));
    }
}
