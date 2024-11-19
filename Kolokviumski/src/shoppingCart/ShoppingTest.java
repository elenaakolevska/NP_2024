package shoppingCart;


import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    WS,
    PS
}

class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }
}

abstract class Product implements Comparable<Product> {
    TYPE type;
    String productID;
    String productName;
    int productPrice;

    public Product(TYPE type, String productID, String productName, int productPrice) {
        this.type = type;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public TYPE getType() {
        return type;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public abstract Double totalPrice();

    public abstract String getDiscountedPrice();

    @Override
    public int compareTo(Product o) {
        return (int) Double.compare(this.totalPrice(), o.totalPrice());
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", productID, totalPrice());
    }
}

class WSProduct extends Product {
    int quantity;

    public WSProduct(TYPE type, String productID, String productName, int productPrice, int quantity) {
        super(type, productID, productName, productPrice);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double totalPrice() {
        return (double) (productPrice * quantity);
    }

    @Override
    public String getDiscountedPrice() {
        return String.format("%s - %.2f", productID, totalPrice() - (this.totalPrice() - this.totalPrice()*0.1)) ;
    }
}

class PSProduct extends Product {

    Double quantity;

    public PSProduct(TYPE type, String productID, String productName, int productPrice, Double quantity) {
        super(type, productID, productName, productPrice);
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity / 1000;
    }

    public Double totalPrice() {
        return productPrice * getQuantity();
    }

    @Override
    public String getDiscountedPrice() {
        return String.format("%s - %.2f", productID, totalPrice() - (this.totalPrice() - this.totalPrice()*0.1)) ;
    }
//this.priceTotal()-(this.priceTotal() - this.priceTotal()*0.1
}

class ShoppingCart {
    List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addItem(String itemData) throws InvalidOperationException {

        String[] parts = itemData.split(";");
        TYPE type = TYPE.valueOf(parts[0]);
        String productID = parts[1];
        String productName = parts[2];
        int productPrice = Integer.parseInt(parts[3]);
        double quantity = Double.parseDouble(parts[4]);

        if (quantity == 0)
            throw new InvalidOperationException(String.format("The quantity of the product with id %s can not be 0.", productID));
        if (type.equals(TYPE.WS)) {

            products.add(new WSProduct(type, productID, productName, productPrice, (int) quantity));
        } else if (type.equals(TYPE.PS)) {

            products.add(new PSProduct(type, productID, productName, productPrice, quantity));
        }
    }

    public void printShoppingCart(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);

        products.stream().sorted(Comparator.reverseOrder()).forEach(p -> {
            pw.println(p.toString());
        });

        pw.flush();
        pw.close();
    }

    public void blackFridayOffer(List<Integer> discountItems, PrintStream out) throws InvalidOperationException {
        PrintWriter pw = new PrintWriter(out);
        if (discountItems.isEmpty()) throw new InvalidOperationException("There are no products with discount.");

        products.stream().filter(p-> discountItems.contains(Integer.parseInt(p.productID))).
                forEach(p->{
                    pw.println(p.getDiscountedPrice());
                });
        pw.flush();
        pw.close();
    }
}

public class ShoppingTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        int items = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < items; i++) {
            try {
                cart.addItem(sc.nextLine());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Integer> discountItems = new ArrayList<>();
        int discountItemsCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < discountItemsCount; i++) {
            discountItems.add(Integer.parseInt(sc.nextLine()));
        }

        int testCase = Integer.parseInt(sc.nextLine());
        if (testCase == 1) {
            cart.printShoppingCart(System.out);
        } else if (testCase == 2) {
            try {
                cart.blackFridayOffer(discountItems, System.out);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid test case");
        }
    }
}