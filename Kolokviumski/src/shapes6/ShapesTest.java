package shapes6;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

enum Color {
    RED, GREEN, BLUE
}

interface Scalable {
    void scale(float scaleFactor);
}

interface Stackable {
    float weight();
}

abstract class Shapes implements Scalable, Stackable, Comparable<Shapes> {

    String shapeId;
    Color color;

    public Shapes(String shapeId, Color color) {
        this.shapeId = shapeId;
        this.color = color;
    }

    @Override
    public abstract void scale(float scaleFactor);

    @Override
    public abstract float weight();

    @Override
    public abstract int compareTo(Shapes o);
}

class Circle extends Shapes {

    float radius;

    public Circle(String shapeId, Color color, float radius) {
        super(shapeId, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius = radius * scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    public int compareTo(Shapes o) {
        return Float.compare(this.weight(), o.weight());
    }
    @Override
    public String toString() {
        return String.format("C: %-5s%-10s%10.2f",shapeId,color,weight());
    }
}

class Rectangle extends Shapes {

    float width;
    float height;

    public Rectangle(String shapeId, Color color, float width, float height) {
        super(shapeId, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(float scaleFactor) {
        height = height * scaleFactor;
        width = width * scaleFactor;
    }

    @Override
    public float weight() {
        return height * width;
    }

    @Override
    public int compareTo(Shapes o) {
        return Float.compare(this.weight(), o.weight());
    }

    public String toString() {
        return String.format("R: %-5s%-10s%10.2f",shapeId,color,weight());
    }
}

class Canvas {
    List<Shapes> shapes;

    public Canvas() {
        shapes = new ArrayList<>();
    }

    public void add(String id, Color color, float radius) {
        shapes.add(new Circle(id, color, radius));
        shapes.sort(Comparator.comparingDouble(Shapes::weight).reversed());
    }

    public void add(String id, Color color, float width, float height) {
        shapes.add(new Rectangle(id, color, width, height));
        shapes.sort(Comparator.comparingDouble(Shapes::weight).reversed());
    }

    public void scale(String id, float scaleFactor) {
        shapes.stream().filter(i->i.shapeId.equals(id)).forEach(i->i.scale(scaleFactor));
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (Shapes shape : shapes) {
            sb.append(shape.toString()).append("\n");
        }
        return sb.toString();
    }
}

public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }
}


