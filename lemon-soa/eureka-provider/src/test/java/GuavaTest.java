import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * GuavaTest
 *
 * @author sjp
 * @date 2019/5/4
 */
public class GuavaTest {

    @Test
    public void testPartition(){
        List<Integer> numbers = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        Iterables.partition(numbers,5).forEach(i -> {
            System.out.println("--- seperator ---");
            i.forEach(System.out::println);
        });
        System.out.println("--- The end ---");
    }
}
