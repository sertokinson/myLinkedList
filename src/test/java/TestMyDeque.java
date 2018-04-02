
import com.sertok.utils.IMyDeque;
import com.sertok.utils.MyDeque;

public class TestMyDeque {
    private static int N = Constant.COUNT_ELEMENT;

    public static void main(String[] args) {
        IMyDeque<Integer> myDeque = new MyDeque<>();

        for (int i = 0; i < N; i++) {
            myDeque.add(i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            myDeque.getLast();
        }
        long finish = System.currentTimeMillis();
        System.out.println("time MyDeque: " + (finish - start));
    }
}
