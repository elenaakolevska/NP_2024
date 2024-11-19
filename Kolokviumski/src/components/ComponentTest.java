package components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}

class Component {
    private String color;
    private int weight;
    private List<Component> components;

    public Component(String color, int weight) {
        components = new ArrayList<>();
        this.color = color;
        this.weight = weight;

    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void addComponent(Component component) {
        components.add(component);
        components.sort(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));

    }

    public void setColor(String color) {
        this.color = color;
    }
    void changeColor(int weight, String color){
        if(this.weight < weight){
            this.color = color;
        }
        components.stream().forEach(c->c.changeColor(weight,color));
    }
    public String print(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%d:%s\n", prefix, weight, color));
        components.stream().forEach(c -> sb.append(c.print(String.format("---%s", prefix))));
        return sb.toString();
    }

// @Override
// public int compareTo(Component o) {
//  return Integer.compare(this.weight, o.weight);
// }
}

class Window {
    private String name;
    private List<Component> components;

    public Window(String name) {
        components = new ArrayList<>();
        this.name = name;

    }

    public void addComponent(int position, Component component) throws InvalidPositionException {
        if (position < 1 || position > components.size() + 1) {
            throw new InvalidPositionException(String.format("Invalid position %d, out of bounds!", position));
        }
        if (components.size() >= position) {
            throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!", position));
        }
        components.add(position - 1, component);  // Convert 1-based position to 0-based
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW ").append(name).append("\n");
        for (int i = 0; i < components.size(); i++) {
            sb.append(i + 1).append(":").append(components.get(i).print(""));
        }
        return sb.toString();
    }
    void changeColor(int weight, String color){
        components.stream().forEach(c->c.changeColor(weight,color));
    }
    void swichComponents(int pos1, int pos2) {
        pos1--; pos2--; // Adjust positions to 0-based index

        List<Component> updatedComponents = new ArrayList<>(components);
        updatedComponents.set(pos1, components.get(pos2));
        updatedComponents.set(pos2, components.get(pos1));

        components.clear();
        components.addAll(updatedComponents);
    }

}


public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

