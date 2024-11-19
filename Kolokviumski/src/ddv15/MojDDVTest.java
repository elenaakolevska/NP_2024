package ddv15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(String message) {
        super(message);
    }
}

enum TYPE {
    A,
    B,
    V
}

class Item {
    int price;
    TYPE type;

    public Item(int price, TYPE type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public TYPE getType() {
        return type;
    }

    public double getTaxPrice() {
        if (type.equals(TYPE.A)) {
            return price * 0.18 * 0.15;
        } else if (type.equals(TYPE.B)) {
            return price * 0.05 * 0.15;
        } else return 0;
    }



}

class Receipt {
    Long id;
    List<Item> items;

    public Receipt() {
        items = new ArrayList<>();
    }

    public Receipt(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String [] parts = line.split("\\s+");
        Long id = Long.valueOf(parts[0]);
        List<Item> itemList = new ArrayList<>();
        int totalPrice =0;
        for (int i = 1; i < parts.length; i+=2) {
            int itemPrice = Integer.parseInt(parts[i]);
            TYPE taxType = TYPE.valueOf(parts[i+1]);
            Item item = new Item(itemPrice, taxType);
            itemList.add(item);
            totalPrice+=itemPrice;
        }
        if(totalPrice>30000) throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned", totalPrice));

        return new Receipt(id, itemList);
    }


    public int totalSum(){
       return items.stream().mapToInt(Item::getPrice).sum();
    }
    public double totalTax(){
            return items.stream().mapToDouble(Item::getTaxPrice).sum();
    }

    @Override
    public String toString() {
        return String.format("%10s\t%10d\t%10.5f", id, totalSum(), totalTax());
    }
}

class MojDDV {
    List<Receipt> receipts;

    public MojDDV() {
        receipts = new ArrayList<>();
    }

    public void readRecords(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

         br.lines().forEach(line -> {
             try {
                 Receipt receipt = Receipt.createReceipt(line);
                 receipts.add(receipt);
             } catch (AmountNotAllowedException e) {
                 System.out.println(e.getMessage());
             }
        });
    }

    public void printTaxReturns (OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        receipts.forEach(r ->pw.println(r.toString()));

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
    }
}
