import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class LineProcessor{

    List<String> lines;

    public LineProcessor(){
        lines=new ArrayList<>();
    }
    public int countOcc(String line, char c){
//        int counter=0;
//        for (char character : line.toLowerCase().toCharArray()){
//            if(character==c){
//                ++counter;
//            }
//        }
//        return counter;

        return (int)line.toLowerCase().chars().filter(i->((char) i ==c)).count();
    }
    void readLines(InputStream is, OutputStream os, char c){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        PrintWriter printWriter = new PrintWriter(os);
        lines= bufferedReader
                .lines()
                .collect(Collectors.toList());

        Comparator<String> comparator = Comparator.comparingInt(left->countOcc(left,c));
        String max=lines.stream()
                .max(comparator.thenComparing(Comparator.naturalOrder())).orElse("");

        printWriter.println(max);
        printWriter.flush();
        printWriter.close();

    }

}

public class LineProcessorTest {

    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();
        lineProcessor.readLines(System.in, System.out, 'a');
    }


}
