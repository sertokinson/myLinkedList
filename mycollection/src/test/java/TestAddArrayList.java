import java.util.ArrayList;
import java.util.List;

public class TestAddArrayList {
    private static long add(){
        List<Integer> arrayList=new ArrayList<>();
        long start=System.currentTimeMillis();
        for (int i = 0; i < Constant.COUNT_ELEMENT; i++) {
            arrayList.add(i);
        }
        long finish=System.currentTimeMillis();
        return finish-start;
    }

    public static void main(String[] args) {
        System.out.println("cold start time " + String.format("%,12d",add()) + " ms");
        System.out.println("warmed JRE time " + String.format("%,12d",add()) + " ms");
    }
}
