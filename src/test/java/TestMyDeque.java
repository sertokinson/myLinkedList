
import com.sertok.utils.IMyDeque;
import com.sertok.utils.MyDeque;

public class TestMyDeque {
    private static int N = Constant.COUNT_ELEMENT;

    public static void main(String[] args) {
        IMyDeque<Integer> myDeque = new MyDeque<>();
        for (int i = 0; i < 10; i++) {
            myDeque.add(i);
          //myDeque.getLast();
        }
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.add(11);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 7; i++) {
            System.out.println(myDeque.get(i));
        }
        long finish = System.currentTimeMillis();
        System.out.println("time MyDeque: " + (finish - start));
    }
}
