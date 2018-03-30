import java.util.ArrayList;
import java.util.List;

public class TestAddArrayList {

    public static void main(String[] args) {
        List<Integer> arrayList=new ArrayList();

        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            arrayList.add(i);
        }
        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            arrayList.get(Constant.COUNT_ELEMENT-1);
        }
        long finish=System.currentTimeMillis();
        System.out.println("time ArrayList: "+(finish-start));
    }

}
