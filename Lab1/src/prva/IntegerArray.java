//package prva;
//
//import java.io.BufferedWriter;
//import java.sql.Array;
//import java.util.Arrays;
//
//final public class IntegerArray {
//    final public int a[];
//
//    public IntegerArray(int[] a) {
//        this.a = new int[a.length];
//    }
//
//    public int length() {
//        return a.length;
//    }
//
//    public int getElementAt(int i) {
//        return a[i];
//    }
//
//    public int sum() {
//        int suma = 0;
//        for (int i = 0; i < a.length; i++) {
//            suma += a[i];
//        }
//        return suma;
//    }
//
//    public double average() {
//        return (double) sum() / a.length;
//    }
//
//    public IntegerArray getSorted(){
//
//        IntegerArray nova = new IntegerArray(a);
//        Arrays.sort(nova.a);
//        return nova;
//    }
//
//    public IntegerArray concat(IntegerArray ia){
//        int []nova = new int[a.length + ia.length()];
//
//        int i;
//        for (i = 0; i < a.length; i++) {
//            nova[i] = a[i];
//        }
//        int j=0;
//
//        for (i = a.length; i < a.length+ia.length(); i++) {
//            nova[i] = ia.getElementAt(j);
//            j++;
//        }
//
//        return new IntegerArray(nova);
//    }
//
//    @Override
//    public String toString() {
//       StringBuilder sb = new StringBuilder();
//       sb.append("[");
//
//        for (int i = 0; i < a.length; i++) {
//            sb.append(a[i]);
//            if(i != a.length-1)
//                sb.append(",");
//        }
//        sb.append("]");
//
//        return sb.toString();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof IntegerArray that)) return false;
//        return Arrays.equals(a, that.a);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(a);
//    }
//}
