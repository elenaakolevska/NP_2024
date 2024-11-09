package prva;

import java.util.*;

class ResizableArray<T>{

    private T[] array;
    @SuppressWarnings("unchecked")
    public ResizableArray() {
        array = (T[]) new  Object[0];
    }

    public void addElement(T element){
        T [] nova = Arrays.copyOf(array, array.length+1);
        nova[nova.length-1] = element;
        array = nova;
    }

    public boolean  removeElement(T element ){
        int index =findElement(element);

        if(index==-1){
            return false;
        }
        else {
            array[index] = array[array.length-1];
            T[] nova = Arrays.copyOf(array, array.length-1);
            array=nova;
            return true;
        }
    }

    public int findElement(T element){
        for (int i = 0; i < array.length; i++) {
            if(element.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    public int count() {
        return array.length;
    }

    public boolean contains(T first) {
        return Arrays.asList(array).contains(first);
    }

    public Object[] toArray(){
        return Arrays.stream(array).toArray();
    }

    public boolean isEmpty(){
        return array.length == 0;
    }


    public T elementAt(int index){
        if(index<0 || index>= array.length) throw new ArrayIndexOutOfBoundsException(index);
        return array[index];
    }
    @SuppressWarnings("unchecked")

    static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        T[] nova = (T[]) Arrays.copyOf(src.toArray(), src.count());

        for (T el : nova){
            dest.addElement(el);
        }
    }
}

class IntegerArray extends ResizableArray<Integer>{
    public double sum(){
        double suma = 0;
        for (int i = 0; i < count(); i++) {
            suma+=elementAt(i);
        }
        return suma;
    }

    public double mean(){
        return sum()/count();
    }

    public int countNonZero(){
        int counter=0;
        for (int i = 0; i < count(); i++) {
            if (elementAt(i) != 0){
                counter++;
            }
        }
        return counter;
    }

    public IntegerArray distinct(){
        IntegerArray arr = new IntegerArray();

        for (int i = 0; i < count(); i++) {
            if (!arr.contains(elementAt(i))){
                arr.addElement(elementAt(i));
            }
        }
        return arr;
    }

    public IntegerArray increment(int offset){

        IntegerArray arr = new IntegerArray();
        for (int i = 0; i < count(); i++) {
            arr.addElement(elementAt(i) + offset);
        }
        return arr;
    }
}
public class ResizableArrayTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}
