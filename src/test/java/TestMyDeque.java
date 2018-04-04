
import com.sertok.utils.IMyDeque;
import com.sertok.utils.MyDeque;

public class TestMyDeque {
    private static int N = Constant.COUNT_ELEMENT;

    public static void main(String[] args) {
        MyDeque<Integer> myDeque = new MyDeque<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            myDeque.add(i);
        }
        long finish = System.currentTimeMillis();
        myDeque.removeLast();
        myDeque.removeLast();
        for (Integer i:myDeque) {
            System.out.println(i);

        }
        System.out.println("time MyDeque: " + (finish - start));
    }
}
