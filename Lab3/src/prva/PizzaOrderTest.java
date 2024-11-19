//package prva;
//
//import javax.swing.*;
//import java.util.*;
//
//interface Item {
//    int getPrice();
//
//    String getType();
//}
//
//class InvalidExtraTypeException extends Exception {
//    public InvalidExtraTypeException(String message) {
//        super(message);
//    }
//}
//
//class ExtraItem implements Item {
//
//    public String type;
//
//    public ExtraItem(String type) throws InvalidExtraTypeException {
//
//        if (!(type.equals("Coke") || type.equals("Ketchup"))) {
//            throw new InvalidExtraTypeException("InvalidExtraTypeException");
//        }
//        this.type = type;
//    }
//
//    @Override
//    public int getPrice() {
//        if (type.equals("Ketchup")) return 3;
//        else return 5;
//    }
//
//    @Override
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ExtraItem)) return false;
//        ExtraItem extraItem = (ExtraItem) o;
//        return Objects.equals(type, extraItem.type);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(type);
//    }
//}
//
//class InvalidPizzaTypeException extends Exception {
//    public InvalidPizzaTypeException(String message) {
//        super(message);
//    }
//}
//
//class PizzaItem implements Item {
//
//    ArrayList<String> pizzaType = new ArrayList<>();
//    public String type;
//    int price;
//
//    public PizzaItem(String type) throws InvalidPizzaTypeException {
//
//        pizzaType.add("Standard");
//        pizzaType.add("Pepperoni");
//        pizzaType.add("Vegetarian");
//
//        if (!pizzaType.contains(type)) {
//            throw new InvalidPizzaTypeException("InvalidPizzaTypeException");
//        }
//
//        this.price = calculatePrice(type);
//        this.type = type;
//    }
//
//    public int calculatePrice(String type) {
//        switch (type) {
//            case "Standard":
//                return 10;
//            case "Pepperoni":
//                return 12;
//            case "Vegetarian":
//                return 8;
//        }
//        return 0;
//    }
//
//    @Override
//    public int getPrice() {
//        return price;
//    }
//
//    @Override
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof PizzaItem)) return false;
//        PizzaItem pizzaItem = (PizzaItem) o;
//        return price == pizzaItem.price &&
//                Objects.equals(pizzaType, pizzaItem.pizzaType) &&
//                Objects.equals(type, pizzaItem.type);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(pizzaType, type, price);
//    }
//}
//
//class Product {
//
//    Item item;
//    int count;
//
//    public Product(Item item, int count) {
//        this.item = item;
//        this.count = count;
//    }
//
//    public Item getItem() {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//
//}
//class OrderLockedException extends Exception{
//    public OrderLockedException() {
//    }
//}
//class EmptyOrder extends Exception{
//    public EmptyOrder() {
//    }
//}
//class Order {
//    public boolean locked;
//    List<Product> products;
//
//
//    public Order() {
//        this.locked = false;
//        this.products = new ArrayList<>();
//    }
//
//    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
//        if (count > 10) {
//            throw new ItemOutOfStockException(item);
//        }
//
//        if(locked){
//            throw  new OrderLockedException();
//        }
//        for (Product p : products) {
//            if (p.getItem().equals(item)) {
//                p.setCount(count);
//                return;
//            }
//        }
//
//        products.add(new Product(item, count));
//    }
//
//    public int getPrice() {
//        return products.
//                stream().
//                mapToInt(i -> i.item.getPrice() * i.getCount()).
//                sum();
//    }
//
//    public void displayOrder(){
//
//        for (int i = 0; i < products.size(); i++) {
//            System.out.println(String.format("%3d.%-15sx%2d%5d$", i+1, products.get(i).item.getType(), products.get(i).count, products.get(i).item.getPrice()*products.get(i).count));
//        }
//        System.out.println(String.format("%-22s%5d$", "Total:", getPrice()));
//    }
//
//    public void removeItem(int idx) throws OrderLockedException {
//        if(locked){
//            throw  new OrderLockedException();
//        }
//        products.remove(idx);
//    }
//
//    public void lock() throws EmptyOrder {
//        if (products.isEmpty()){
//            throw new EmptyOrder();
//        }
//        locked = true;
//    }
//}
//
//
//class ItemOutOfStockException extends Exception {
//    Item item;
//
//    public ItemOutOfStockException(Item item) {
//        this.item = item;
//    }
//}
//
//public class PizzaOrderTest {
//
//    public static void main(String[] args) {
//        Scanner jin = new Scanner(System.in);
//        int k = jin.nextInt();
//        if (k == 0) { //test Item
//            try {
//                String type = jin.next();
//                String name = jin.next();
//                Item item = null;
//                if (type.equals("Pizza")) item = new PizzaItem(name);
//                else item = new ExtraItem(name);
//                System.out.println(item.getPrice());
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//        }
//        if (k == 1) { // test simple order
//            Order order = new Order();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            jin.next();
//            System.out.println(order.getPrice());
//            order.displayOrder();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            System.out.println(order.getPrice());
//            order.displayOrder();
//        }
//        if (k == 2) { // test order with removing
//            Order order = new Order();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            jin.next();
//            System.out.println(order.getPrice());
//            order.displayOrder();
//            while (jin.hasNextInt()) {
//                try {
//                    int idx = jin.nextInt();
//                    order.removeItem(idx);
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            System.out.println(order.getPrice());
//            order.displayOrder();
//        }
//        if (k == 3) { //test locking & exceptions
//            Order order = new Order();
//            try {
//                order.lock();
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.addItem(new ExtraItem("Coke"), 1);
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.lock();
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.removeItem(0);
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//        }
//    }
//
//}