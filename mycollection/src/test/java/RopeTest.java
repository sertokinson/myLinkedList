import org.ahmadsoft.ropes.Rope;
import org.junit.Test;

public class RopeTest {

    @Test
    public void rope(){
        Rope r = Rope.BUILDER.build("Hello World");
        System.err.println(r);
    }

}
