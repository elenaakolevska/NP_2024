//package vtora;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Objects;
//
//final public class DoubleMatrix {
//
//    double[] a;
//    int m;
//    int n;
//    double[][] matrix;
//
//    public DoubleMatrix(double[] a, int m, int n) throws InsufficientElementsException {
//        this.a = a;
//        this.m = m;
//        this.n = n;
//
//        if (a.length < m * n) {
//            throw new InsufficientElementsException();
//        } else if (a.length > m * n) {
//            matrix = new double[m][n];
//            int temp = a.length - m * n;
//
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    this.matrix[i][j] = a[temp];
//                    temp++;
//                }
//            }
//        } else {
//            matrix = new double[m][n];
//            int temp = 0;
//
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    this.matrix[i][j] = a[temp];
//                    temp++;
//                }
//            }
//        }
//    }
//
//    public String getDimensions() {
//        return "[" + m + " x " + n + "]";
//    }
//
//    public int rows() {
//        return m;
//    }
//
//    public int columns() {
//        return n;
//    }
//
//    public double maxElementAtRow(int row) throws InvalidRowNumberException {
//        if (row >= m || row <= 1) {
//            throw new InvalidRowNumberException();
//        }
//
//        double max = matrix[0][0];
//        for (int i = 0; i < n; i++) {
//            if (max <= matrix[row][i]) {
//                max = matrix[row][i];
//            }
//        }
//        return max;
//    }
//
//    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
//        if (column >= n || column <= 1) {
//            throw new InvalidColumnNumberException();
//        }
//
//        double max = matrix[0][0];
//        for (int i = 0; i < m; i++) {
//            if (max <= matrix[i][column]) {
//                max = matrix[i][column];
//            }
//        }
//        return max;
//    }
//
//    public double sum() {
//        double suma = 0.0;
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                suma += matrix[i][j];
//            }
//        }
//        return suma;
//    }
//
//    public double[] toSortedArray() {
//        double[] a = new double[m * n];
//        int temp = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                a[temp++] = matrix[i][j];
//            }
//        }
//
//        Arrays.sort(a);
//
//        for (int i = 0; i < a.length / 2; i++) {
//            double temp2 = a[i];
//            a[i] = a[a.length - 1 - i];
//            a[a.length - 1 - i] = temp2;
//        }
//        return a;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DoubleMatrix that = (DoubleMatrix) o;
//        return m == that.m && n == that.n && Arrays.deepEquals(matrix, that.matrix);
//    }
//
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(m, n);
//        result = 31 * result + Arrays.hashCode(a);
//        result = 31 * result + Arrays.hashCode(matrix);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                sb.append(String.format("%.2f", matrix[i][j]));
//                if (j != n - 1) {
//                    sb.append("\t");
//                }
//            }
//            if (i != m - 1)
//                sb.append("\n");
//        }
//        return sb.toString();
//    }
//}
