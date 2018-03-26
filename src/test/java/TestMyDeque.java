
import com.sertok.utils.IMyDeque;
import com.sertok.utils.MyDeque;

public class TestMyDeque {
    private static int N = Constant.COUNT_ELEMENT;

    public static void main(String[] args) {
        IMyDeque<Integer> myDeque = new MyDeque<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            myDeque.add(i);
          //myDeque.getLast();
        }
        System.out.println(myDeque.get(0));
        System.out.println(myDeque.get(3));
        System.out.println(myDeque.get(4));
        long finish = System.currentTimeMillis();
        System.out.println("time MyDeque: " + (finish - start));
    }
}
