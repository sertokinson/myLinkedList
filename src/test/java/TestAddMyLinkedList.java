
import com.sertok.utils.IMyLinkedList;
import com.sertok.utils.MyLinkedList;

public class TestAddMyLinkedList {
    private static int N = 9;

    public static void main(String[] args) {
        IMyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            myLinkedList.add(i);
          // myLinkedList.get(i/2);
        }
        for (int i = 0; i < N; i++) {
            System.out.println(myLinkedList.get(i));
            // myLinkedList.get(i/2);
        }
        myLinkedList.removeLast();
        myLinkedList.removeLast();
        myLinkedList.removeLast();
        myLinkedList.removeLast();
        myLinkedList.removeLast();
        System.out.println("last="+myLinkedList.getLast());
        System.out.println("size="+myLinkedList.size());
        long finish = System.currentTimeMillis();
        System.out.println("time MyLinkedList: " + (finish - start));
    }
}
