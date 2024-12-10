package aud8;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
1.2. Reverse list
Да се напише метод за печатење на колекција во обратен редослед со
помош на Collections API но без употреба на ListIterator.
 */
public class ReverseListTest {

    public static <T> void reversePrint(Collection<T> collection){
        List<T> list = new ArrayList<>(collection);

//        for (int i = list.size()-1; i >= 0; i--) {
//            System.out.println(list.get(i));
//        }
        Collections.reverse(list);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> ints = List.of(1,2,3,4,5,6,7,8,9,10);
        reversePrint(ints);
    }
}
