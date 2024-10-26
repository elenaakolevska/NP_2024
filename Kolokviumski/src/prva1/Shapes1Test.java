package prva1;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class Square{
    int side;

    public Square(int side) {
        this.side = side;
    }

    public int getSide() {
        return 4*side;
    }
}
class Canvas implements Comparable<Canvas>{
    String canvas_id;
    List<Square> squares;

    public Canvas(String canvas_id, List<Square> squares) {
        this.canvas_id = canvas_id;
        this.squares = squares;
    }

    public static Canvas createCanvas(String line){
        // 364fbe94 24 30 22 33 32 30 37 18 29 27 33 21 27 26
        String[] parts = line.split("\\s+");
        String id =parts[0];

        List<Square> squares =Arrays.stream(parts)
                .skip(1)
                .map(p -> Integer.parseInt(p))
                .map(side -> new Square(side))
                .collect(Collectors.toList());


        return new Canvas(id, squares);
    }


    public String getCanvas_id() {
        return canvas_id;
    }

    public void setCanvas_id(String canvas_id) {
        this.canvas_id = canvas_id;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", canvas_id, squares.size(), sumPerimetres());
    }

    public int sumPerimetres(){
        return squares.stream()
                .mapToInt(square -> square.getSide())
                .sum();
    }
    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(this.sumPerimetres(), o.sumPerimetres());
    }
}

class ShapesApplication {

    List<Canvas> canvases;

    public ShapesApplication() {
    }


    public int readCanvases(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
       canvases  = br.lines()
                .map(c->Canvas.createCanvas(c))
                .collect(Collectors.toList());

        return canvases
                .stream()
                .mapToInt(canvases -> canvases.squares.size()).sum();

    }

    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        Canvas max = canvases.stream().max(Comparator.naturalOrder()).get();
        pw.println(max);
        pw.flush();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }


}
