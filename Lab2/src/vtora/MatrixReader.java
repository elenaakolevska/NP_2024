//package vtora;
//
//import java.io.InputStream;
//import java.util.Scanner;
//
//public class MatrixReader {
//    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
//        Scanner scanner = new Scanner(input);
//
//        int m = scanner.nextInt();
//        int n = scanner.nextInt();
//        double []niza = new double[m*n];
//        for (int i = 0; i < m*n; i++) {
//            niza[i] = scanner.nextDouble();
//        }
//
//        return new DoubleMatrix(niza, m,n);
//    }
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
//}
