import java.util.ArrayList;
import java.util.List;

public class TestAddArrayList {

    public static void main(String[] args) {
        List<Integer> arrayList=new ArrayList(Constant.COUNT_ELEMENT);
        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            arrayList.get(0);
        }

        long finish=System.currentTimeMillis();
        System.out.println("time ArrayList: "+(finish-start));
    }

}
