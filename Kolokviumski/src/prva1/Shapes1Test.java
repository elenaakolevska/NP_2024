package prva1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Square {
    int side;

    public Square(int side) {
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public int getPerimeter() {
        return side * 4;
    }


}

class Canvas {
    String canvasId;
    List<Square> squares;

    public Canvas() {
        this.squares = new ArrayList<>();
    }

    public Canvas(String canvasId, List<Square> squares) {
        this.canvasId = canvasId;
        this.squares = squares;
    }

    public static Canvas createCanvas(String line) {
        String[] parts = line.split("\\s+");
        String canvasId = parts[0];
        List<Square> squares = new ArrayList<>();

        for (int i = 1; i < parts.length; i++) {
            squares.add(new Square(Integer.parseInt(parts[i])));
        }
        return new Canvas(canvasId, squares);
    }

    public int sumPerimeter(){
        return squares.stream().mapToInt(i->i.getPerimeter()).sum();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", canvasId, squares.size(), sumPerimeter());
    }
}

class ShapesApplication {
    List<Canvas> canvas;

    public ShapesApplication() {
        canvas = new ArrayList<>();
    }

    public int readCanvases(InputStream in) {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        canvas = br.
                lines().
                map(Canvas::createCanvas).
                collect(Collectors.toList());

        return canvas.
                stream().
                mapToInt(c -> c.squares.size()).
                sum();
    }

    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        Canvas max = canvas.
                stream().
                max(Comparator.comparing(Canvas::sumPerimeter)).
                get();
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