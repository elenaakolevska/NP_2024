package shapes2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

enum TYPE {
    S,
    C
}


class IrregularCanvasException extends Exception {
    IrregularCanvasException(String id, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", id, maxArea));
    }
}
abstract class Shape {

    int size;

    public Shape(int size) {

        this.size = size;
    }

    abstract TYPE getType();
    public abstract double getSize();

    public static Shape createShape(int size, char type, double maxArea){
        switch (type){
            case 'S': return new Square(size);
            case 'C' : return new Circle(size);
            default: return null;
        }
    }

}

class Square extends Shape {

    public Square(int size) {
        super(size);

    }

    @Override
    public TYPE getType() {
        return TYPE.S;
    }

    @Override
    public double getSize() {
        return size * size;
    }
}

class Circle extends Shape {
    int radius;

    public Circle(int size) {
        super(size);

    }


    @Override
    public TYPE getType() {
        return TYPE.C;
    }

    @Override
    public double getSize() {
        return Math.PI * size * size;
    }
}

class Canvas implements Comparable<Canvas>{
    String canvasId;
    List<Shape> shapeList;

    public Canvas() {
        this.shapeList = new ArrayList<>();

    }

    public Canvas(String canvasId, List<Shape> shapeList) {
        this.canvasId = canvasId;
        this.shapeList = shapeList;
    }

    public Canvas(String canvasId) {
        this.canvasId = canvasId;

    }

    public Canvas createCanvases(String line, double maxArea) throws IrregularCanvasException {
        String[] parts = line.split("\\s+");
        String canvasId = parts[0];

        List<Shape> shapes= new ArrayList<>();
        for (int i = 1; i < parts.length; i += 2) {
            Shape shape = Shape.createShape(Integer.parseInt(parts[i+1]), parts[i].charAt(0), maxArea);

            if (shape.getSize() > maxArea) throw new IrregularCanvasException(canvasId, maxArea);
            shapes.add(Shape.createShape(Integer.parseInt(parts[i+1]), parts[i].charAt(0), maxArea));
        }
        return new Canvas(canvasId, shapes);
    }

    int getCirclesCount() {
        return (int) shapeList.stream().filter(s -> s.getType().equals(TYPE.C)).count();
    }

    @Override
    public int compareTo(Canvas o) {
        return Double.compare(this.shapeList.stream().mapToDouble(Shape::getSize).sum(),
        o.shapeList.stream().mapToDouble(Shape::getSize).sum());
    }

    @Override
    public String toString() {
        //ID total_shapes total_circles total_squares min_area max_area average_area
        DoubleSummaryStatistics dss = shapeList.stream().mapToDouble(Shape::getSize).summaryStatistics();
        return String.format("%s %d %d %d %.2f %.2f %.2f",
                canvasId,
                shapeList.size(),
                getCirclesCount(),
                shapeList.size() - getCirclesCount(),
                dss.getMin(),
                dss.getMax(),
                dss.getAverage());
    }
}


class ShapesApplication {

    private double maxArea;
    private List<Canvas> canvasList;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvasList = new ArrayList<>();
    }

    public void readCanvases(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
       canvasList = br.lines().map(line -> {
            Canvas canvas = new Canvas();
            try {
                return canvas.createCanvases(line, maxArea);
            } catch (IrregularCanvasException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    public void printCanvases(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        canvasList.stream().sorted(Comparator.reverseOrder()).forEach(pw::println);

        pw.flush();
        pw.close();
    }
}


public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}