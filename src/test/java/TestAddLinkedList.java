import java.util.LinkedList;
import java.util.List;


public class TestAddLinkedList {

    public static void main(String[] args) {
        List linkedList=new LinkedList();

        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            linkedList.add(i);
        }
        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            linkedList.get(Constant.COUNT_ELEMENT-1);
        }
        long finish=System.currentTimeMillis();
        System.out.println("time LinkedList: "+(finish-start));
    }
}
