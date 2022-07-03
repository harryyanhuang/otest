import java.util.ArrayList;
import java.util.List;

public class Person {
     int[] arr=new int[10];
    List<Integer> list=new ArrayList<>(100);

    public static void main(String[] args) {
//        Person person = new Person();
//        System.out.println(person.arr[2]);
//        System.out.println(arr[1]);
        int i=10;int n= (++i)%5;

        System.out.println(i);
        System.out.println(n);
    }
}
