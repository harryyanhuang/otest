import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args){
        List<Integer> list=new ArrayList<>(100);
        list.add(1);
        list.add(2);
        list.remove(1);
        list.add(3);
        System.out.println(Arrays.toString(list.toArray()));
        list.set(1,7);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
        list.add(1,5);
        System.out.println(list.size());
        System.out.println(list.get(1));
        System.out.println(list.size());
        System.out.println(Arrays.toString(list.toArray()));
        List<Integer> link=new LinkedList<>();
        link.add(1);
        link.add(2);
        link.add(3);

        System.out.println(link.containsAll(Arrays.asList(2,5)));
        System.out.println(link.contains(8));

        System.out.println(""+'a'+1);
    }
}
