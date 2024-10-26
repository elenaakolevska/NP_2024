package mobilenOperator;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class InvalidException extends  Exception{

    public InvalidException(String message) {
        super(message);
    }
}
abstract class Customer {
    String id;
    double minutes;
    int SMSs;
    double GBs;

    private static boolean isIdValid(String id){
        if(id.length()!=7){
            return false;
        }
        for (int i = 0; i < 7; i++) {
            if(!Character.isDigit(id.charAt(i)))
                return false;
        }
        return true;
    }

    public Customer(String id, double minutes, int SMSs, double GBs) throws InvalidException {
       if(!isIdValid(id))
           throw new InvalidException(String.format("%s is not a valid sales rep ID", id));
        this.id = id;
        this.minutes = minutes;
        this.SMSs = SMSs;
        this.GBs = GBs;
    }

    abstract double totalPrice();

    abstract double commission();
}

class SCustomer extends Customer {

    static double BASE_PRICE_S = 500.0;
    static double FREE_MINUTES_S = 100.0;
    static double FREE_SMS_S = 50;
    static double FREE_GBs_INTERNET_S = 5.0;
    static double PRICE_PER_MINUTE = 5.0;
    static double PRICE_PER_SMS = 6.0;
    static double PRICE_PER_GB = 25.0;
    static double COMMISION_RATE = 0.07;

    public SCustomer(String id, double minutes, int SMSs, double GBs) throws InvalidException {
        super(id, minutes, SMSs, GBs);
    }

    @Override
    double totalPrice() {
        double total = BASE_PRICE_S;

        total += PRICE_PER_MINUTE * Math.max(0, minutes - FREE_MINUTES_S);

//        if(minutes>FREE_MINUTES_S){
//            total+=(PRICE_PER_MINUTE*(minutes-FREE_MINUTES_S));
//        }
        if (SMSs > FREE_SMS_S) {
            total += (PRICE_PER_SMS * (SMSs - FREE_SMS_S));
        }
        if (GBs > FREE_GBs_INTERNET_S) {
            total += (PRICE_PER_GB * (GBs - FREE_GBs_INTERNET_S));
        }

        return total;
    }

    @Override
    double commission() {
        return totalPrice() * COMMISION_RATE;
    }
}

class MCustomer extends Customer {

    static double BASE_PRICE_M = 750.0;
    static double FREE_MINUTES_M = 150.0;
    static double FREE_SMS_M = 60;
    static double FREE_GBs_INTERNET_M = 10.0;
    static double PRICE_PER_MINUTE = 4.0;
    static double PRICE_PER_SMS = 4.0;
    static double PRICE_PER_GB = 20.0;

    static double COMMISSION_RATE = 0.04;

    public MCustomer(String id, double minutes, int SMSs, double GBs) throws InvalidException {
        super(id, minutes, SMSs, GBs);
    }

    @Override
    double totalPrice() {
        double total = BASE_PRICE_M;

        total += PRICE_PER_MINUTE * Math.max(0, minutes - FREE_MINUTES_M);

//        if(minutes>FREE_MINUTES_S){
//            total+=(PRICE_PER_MINUTE*(minutes-FREE_MINUTES_S));
//        }
        if (SMSs > FREE_SMS_M) {
            total += (PRICE_PER_SMS * (SMSs - FREE_SMS_M));
        }
        if (GBs > FREE_GBs_INTERNET_M) {
            total += (PRICE_PER_GB * (GBs - FREE_GBs_INTERNET_M));
        }

        return total;
    }

    @Override
    double commission() {
        return totalPrice() * COMMISSION_RATE;
    }
}

class SalesRep implements Comparable<SalesRep> {
    String id;
    List<Customer> customers;

    public SalesRep(String id, List<Customer> customers) {
        this.id = id;
        this.customers = customers;
    }

    private static boolean isValid(String id) throws InvalidException {
        if(id.length()!=3){
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if(!Character.isDigit(id.charAt(i)))
                return false;
        }
        return true;
    }
    public static SalesRep createSalesRep(String line) throws InvalidException {
        String[] parts = line.split("\\s+");
        String id = parts[0];

        if(!isValid(id)){
            throw new InvalidException(String.format("%s is not a valid sales rep ID", id));
        }

        List<Customer> customers = new ArrayList<>();

        for (int i = 1; i < parts.length; i += 5) {
            String customerId = parts[i];
            String type = parts[i + 1];
            double minutes = Double.parseDouble(parts[i + 2]);
            int sms = Integer.parseInt(parts[i + 3]);
            double gbs = Double.parseDouble(parts[i + 4]);
            try{
                if (type.equals("M")) {
                    customers.add(new MCustomer(customerId, minutes, sms, gbs));
                } else customers.add(new SCustomer(customerId, minutes, sms, gbs));
            }catch (InvalidException e){
                System.out.println(e.getMessage());
            }

        }

        return new SalesRep(id, customers);
    }

    @Override
    public String toString() {
      DoubleSummaryStatistics summaryStatistics= customers.stream().mapToDouble(custormer->custormer.totalPrice()).summaryStatistics();
        return String.format("%s Count: %d Min: %.2f Average: %.2f Max: %.2f Commission: %.2f",
                id, summaryStatistics.getCount(),
                summaryStatistics.getMin(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                totalCommision());
    }

    private double totalCommision(){
        return customers.stream().mapToDouble(customers->customers.commission()).sum();
    }

    @Override
    public int compareTo(SalesRep o) {
        return Double.compare(this.totalCommision(), o.totalCommision());

    }
}

class MobileOperator {
    List<SalesRep> salesReps;

    public MobileOperator() {
    }

    public void readSalesRepData(InputStream in) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        salesReps = br.lines()
                .map(line -> {
                    try {
                        return SalesRep.createSalesRep(line);
                    } catch (InvalidException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        br.close();
    }

    public void printSalesReport(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);

        salesReps
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(salesRep -> pw.println(salesRep));
    }
}

public class MobileOperatorTest {
    public static void main(String[] args) {
        MobileOperator mobileOperator = new MobileOperator();
        System.out.println("---- READING OF THE SALES REPORTS ----");
        try {
            mobileOperator.readSalesRepData(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---- PRINTING FINAL REPORTS FOR SALES REPRESENTATIVES ----");
        mobileOperator.printSalesReport(System.out);
    }

}
