package ddv_16;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(String message) {
        super(message);
    }
}

class Item{
   private int itemPrice;
   private String taxType;

    public Item(int itemPrice, String taxType) {
        this.itemPrice = itemPrice;
        this.taxType = taxType;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getTaxType() {
        return taxType;
    }

    public double calculateTax(){
        if(taxType.equals("A")) return itemPrice * 0.15 * 0.18;
        else if (taxType.equals("B")) return itemPrice * 0.15 * 0.05;
        else return 0;
    }
}

class User{
    private String userId;
     List<Item> items;

    public User(String userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public static User createUser(String line) throws AmountNotAllowedException {
        String[] parts = line.split("\\s+");
        String userId = parts[0];
        List<Item> itemList = new ArrayList<>();
        int totalPrice =0;

        for (int i = 1; i < parts.length; i+=2) {
            int itemPrice = Integer.parseInt(parts[i]);
            String taxType = parts[i+1];

            Item item = new Item(itemPrice, taxType);
            itemList.add(item);

            totalPrice += itemPrice;

        }
        if(totalPrice > 30000) throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned", totalPrice));

        return new User(userId, itemList);
    }

    public int totalSum(){
        return items.stream().mapToInt(Item::getItemPrice).sum();
    }

    public double totalTax(){
        return items.stream().mapToDouble(Item::calculateTax).sum();
    }
    @Override
    public String toString() {
        return String.format("%10s\t%10d\t%10.5f",userId,totalSum(),totalTax());
    }
}

class MojDDV{

    private List<User> users;

    public MojDDV() {
        users = new ArrayList<>();
    }

    public void readRecords(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        br.lines().forEach(line ->{
            try {
                User user = User.createUser(line);
                users.add(user);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        });

    }

    public void printTaxReturns(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        users.forEach(u ->{
            pw.println(u.toString());
        });

        pw.flush();

    }

    public void printStatistics(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        DoubleSummaryStatistics doubleSummaryStatistics = users.stream().mapToDouble(User::totalTax).summaryStatistics();

        pw.println(String.format("min:\t%.3f", doubleSummaryStatistics.getMin()));
        pw.println(String.format("max:\t%.3f", doubleSummaryStatistics.getMax()));
        pw.println(String.format("sum:\t%.3f", doubleSummaryStatistics.getSum()));
        pw.println(String.format("count:\t%d", doubleSummaryStatistics.getCount()));
        pw.println(String.format("avg:\t%.3f", doubleSummaryStatistics.getAverage()));


        pw.flush();
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}
