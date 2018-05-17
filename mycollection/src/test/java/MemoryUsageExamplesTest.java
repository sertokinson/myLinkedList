
import com.sertok.utils.MyDeque;
import org.junit.Test;

import java.util.*;

public class MemoryUsageExamplesTest {
    @Test
    public void testArray() {
        System.out.printf("The average memory used by new ArrayList<Integer> is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                ArrayList<Integer> array = new ArrayList<>(Constant.COUNT_ELEMENT);
                for (int i = 0; i < Constant.COUNT_ELEMENT; i++)
                    array.add(i);
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average memory used by new LinkedList<Integer>() is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                LinkedList<Integer> array = new LinkedList<>();
                if (Constant.COUNT_ELEMENT < 10000000)
                    for (int i = 0; i < Constant.COUNT_ELEMENT; i++)
                        array.add(i);
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average memory used by new Deque<Integer>() is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                Deque<Integer> array = new ArrayDeque<>();
                for (int i = 0; i < Constant.COUNT_ELEMENT; i++)
                    array.add(i);
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average memory used by new MyDeque<Integer>() is %.1f bytes%n", new SizeofUtil() {
            @Override
            protected int create() {
                MyDeque<Integer> array = new MyDeque<>();
                for (int i = 0; i < Constant.COUNT_ELEMENT; i++)
                    array.add(i);
                return 1;
            }
        }.averageBytes());

    }
}
