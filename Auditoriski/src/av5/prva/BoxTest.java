package av5.prva;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/*
    V - value
    E - element
    T - type
*/

interface Drawable<T> {
    T drawElement();
}

class Circle implements Drawable<Circle>{


    @Override
    public Circle drawElement() {
        return this;
    }
}
 class Box<E extends Drawable> {

    private List<E> elements;
    public static Random random = new Random();

    public Box() {
        elements = new ArrayList<>();
    }

    public void add(E element) {
        elements.add(element);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public E drawElement() {
        if (isEmpty()) return null;

//        int index = random.nextInt(elements.size());
//        E elem = elements.get(index);
//        elements.remove(elem);
//        return elem;

    return elements.remove(random.nextInt(elements.size()));

    }
}

public class BoxTest{
    public static void main(String[] args) {
        Box<Circle> box = new Box<>();

        //kreirame stream od integeri
        IntStream.range(0,100)
                .forEach(i->new Circle());
        IntStream.range(0,103)
                .forEach(i-> System.out.println(box.drawElement()));


    }
}
