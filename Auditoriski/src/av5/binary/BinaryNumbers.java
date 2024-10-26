package av5.binary;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.zip.InflaterOutputStream;

public class BinaryNumbers {

    public static final String FILE_NAME = "C:\\Users\\Elence\\Desktop\\NP\\Auditoriski\\src\\av5\\numbers.txt";

    private static void generateFile(int n) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            Random random = new Random();

            // so stream
//            IntStream.range(0,n)
//                    .forEach(i ->{
//            int nextRandom = random.nextInt(10);
//                        try {
//                            objectOutputStream.writeInt(nextRandom);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });

            for (int i = 0; i < n; i++) {
                //generiraj random brojoj i zapisi gi vo objectOutputStream
                int nextRandom = random.nextInt(10);
                objectOutputStream.writeInt(nextRandom);
            }

            objectOutputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double average() {
        int count = 0;
        double sum = 0;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));

            //stavame try i catch kaj while za da ni zavrsit so citanje
            // i da mojme da ja naprajme presmetkata
            try {
                while (true) {
                    int number = objectInputStream.readInt();
                    sum += number;
                    count++;
                }
            } catch (EOFException e) {
                System.out.println("End of File was reached.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sum / count;
    }

    public static void main(String[] args) {
        generateFile(1000);
        System.out.println("Average: " + average());
    }
}
