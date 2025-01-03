package car;

import java.util.*;
import java.util.stream.Collectors;

class Car  implements Comparable<Car>{
    private String manufacturer;
    private String model;
    private int price;
    private float power;


    public Car(String manufacturer, String model, int price, float power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Float.compare(car.price, price) == 0 &&
                Float.compare(car.power, power) == 0 &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(model, car.model);
    }


    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, price, power);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%.0fKW) %d", manufacturer, model, power, price);
    }

    @Override
    public int compareTo(Car o) {
        if(this.price == o.price){
            return Float.compare(this.power, o.power);
        }
        return Integer.compare(this.price, o.price);
    }

    public int compareModel(Car other){
        return this.model.compareToIgnoreCase(other.model);
    }
}


class CarCollection{
    private List<Car> cars;

    public CarCollection() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void sortByPrice(boolean ascending){
        if(ascending){
            cars.sort(Comparator.naturalOrder());
        }
        else cars.sort(Comparator.reverseOrder());
    }

    public List<Car> filterByManufacturer(String manufacturer){
        return cars.stream().
                filter(i->i.getManufacturer().
                        equalsIgnoreCase(manufacturer)).
                sorted(Car::compareModel).
                collect(Collectors.toList());
    }

    public List<Car> getList(){
        return cars;
    }

}

public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}


// vashiot kod ovde