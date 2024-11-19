package petta;

import java.util.Scanner;

class MinMax<T extends Comparable<T>>{

    T min;
    T max;
    int updates;
    int counterMax;
    int counterMin;

    public MinMax() {
        updates = 0;
        counterMax = 0;
        counterMin = 0;
    }

    public void update(T element){
        if(updates==0) {
            max = element;
            min = element;
        }
        if (min.compareTo(element) > 0){
            min = element;
            counterMin =1;
        }
        else if (min.compareTo(element) == 0)counterMin++;

        if (max.compareTo(element) < 0){
            max = element;
            counterMax =1;
        }
        else if (max.compareTo(element) == 0)counterMax++;

       updates++;
    }

    public T max(){
        return max;
    }
    public T min(){
        return min;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", min, max, updates-counterMin-counterMax);
    }
}


public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for (int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
