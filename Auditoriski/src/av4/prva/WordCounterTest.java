package av4.prva;

import java.io.*;
import java.util.function.Consumer;

//class WordCounter{
//    public static void count(InputStream is){
//        //kje raboti za bilo kakov tip na input stream
//
//        Scanner sc = new Scanner(is);
//        int l=0, w=0;
//
//        int c=0;
//
//        while (sc.hasNextLine()){
//            String line = sc.nextLine();
//            ++l;
//            w+= line.split("\\s+").length; //edno ili povekje prazni mesta
//            c+=line.length();
//        }
//        System.out.println(String.format("Lines: %d, Words: %d, Characters %d", l,w,c));
//    }
//}

class LineConsumer implements Consumer<String> {

    int lines = 0, words = 0, characters = 0;

    @Override
    public void accept(String s) {
        ++lines;
        words += s.split("\\s+").length;
        characters += s.length();
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Characters %d", lines, words, characters);

    }

    public static void countWithStream(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        LineConsumer consumer = new LineConsumer();
        br.lines().forEach(consumer);
        System.out.println(consumer);
    }
}

public class WordCounterTest {
    public static void main(String[] args) {
        try {
            InputStream isFromFile = new FileInputStream(new File("C:\\Users\\Elence\\Desktop\\NP\\Auditoriski\\src\\av4\\source.txt"));
            // WordCounter.count(isFromFile);
            LineConsumer.countWithStream(isFromFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
