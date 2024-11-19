package treta;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class IntegerList{
    List<Integer> list;

    public IntegerList() {
        list = new ArrayList<>();
    }
    public IntegerList(Integer... numbers){
        list = new ArrayList<>(Arrays.asList(numbers));
    }

    public void add(int el, int idx){
        if (idx > list.size()){
            IntStream.range(list.size(), idx).forEach(i->list.add(i, 0));
        }
            list.add(idx,el);
    }

    public int remove(int idx){
        return list.remove(idx);
    }
    public int set(int el, int idx){
        return list.set(idx, el);
    }

    public int get(int idx){
        return list.get(idx);
    }
    public int size(){
        return list.size();
    }

    public int count(int el){
        int count=0;
        for (Integer integer : list) {
            if (integer.equals(el)) count++;
        }
        return count;
    }

    public void removeDuplicates(){
        Collections.reverse(list);
        list=list.stream().distinct().collect(Collectors.toList());
        Collections.reverse(list);
    }

    public int sumFirst(int k){
//        int sum=0;
//        for (int i = 0; i < k; i++) {
//            sum+= list.get(i);
//        }
//        return sum;

        return list.stream().limit(k).mapToInt(i-> i).sum();
    }

    public int sumLast(int k){
        return list.stream().skip(Math.max(0, list.size() -k))
                .mapToInt(i->i)
                .sum();
    }

    public void checkIndex(int index){
        if(index < 0 || index> list.size()) throw new ArrayIndexOutOfBoundsException();
    }

    public void shiftRight(int idx, int k){
        checkIndex(idx);
        int n = list.size();
        if(n==0 || k%n == 0)return;
        int newPos = (idx + k) % n;
        int elem = list.remove(idx);

        list.add(newPos, elem);
    }

    public void shiftLeft(int idx, int k){
        checkIndex(idx);
        int n = list.size();
        if(n==0 || k%n == 0)return;
        int newPos = (idx - k) % n;
        if (newPos < 0) newPos+=n;
        int elem = list.remove(idx);
        list.add(newPos, elem);
    }

    public IntegerList addValue(int value){
        IntegerList list1 = new IntegerList();
         list1.list = list.stream().map(i->i+value).collect(Collectors.toList());
         return list1;
    }


}
public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}