//package vtora;
//
//import java.util.Arrays;
//import java.util.Objects;
//
//public class Bank {
//
//    public String name;
//    public Account []accounts;
//    public double totalTransfers;
//    public double totalProvision;
//
//    public Bank(String name, Account[] accounts) {
//        this.name = name;
//        this.accounts = new Account[accounts.length];
//        this.totalProvision=0;
//        this.totalTransfers=0;
//        for (int i = 0; i < accounts.length; i++) {this.accounts[i] = accounts[i];}
//    }
//
//    public double parseStringToDouble(String s){
//        return Double.parseDouble(s.substring(0,s.length()-1));
//    }
//
//    public boolean makeTransaction(Transaction t){
//
//        int indexFrom = findId(t.getFromId());
//        int indexTo = findId(t.getToId());
//
//        // dali postojat dvete smetki
//        if(indexFrom == -1 || indexTo == -1){
//            return false;
//        }
//
//        //zemi balance na site
//        double balaceFrom = parseStringToDouble(accounts[indexFrom].getBalance());
//        double balaceTo = parseStringToDouble(accounts[indexTo].getBalance());
//        double transactionAmount = parseStringToDouble(t.getAmount());
//
//        //dali ima dovolno sredstva
//
//        if(balaceFrom<transactionAmount) return false;
//
//        double provision = t.getProvision();
//
//        totalProvision+=provision;
//        totalTransfers+=transactionAmount;
//
//        if (indexFrom==indexTo){
//            accounts[indexFrom].setBalance(String.format("%.2f$", balaceFrom - provision));
//        }
//        else {
//            accounts[indexFrom].setBalance(String.format("%.2f$", balaceFrom - provision - transactionAmount));
//            accounts[indexTo].setBalance(String.format("%.2f$", balaceTo + transactionAmount));
//        }
//
//        return true;
//    }
//
//    public String totalTransfers() {
//        return String.format("%.2f$", totalTransfers);
//    }
//
//    public String totalProvision() {
//        return String.format("%.2f$", totalProvision);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Name:").append(name).append("\n\n");
//        sb.append(Arrays.toString(accounts));
//        return sb.toString();
//    }
//
//    public int findId(long Id){
//        int index=-1;
//        for (int i = 0; i < accounts.length; i++) {
//            if(accounts[i].getId()==Id){
//                index=i;
//                break;
//            }
//        }
//        return index;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Bank bank)) return false;
//        return Double.compare(totalTransfers, bank.totalTransfers) == 0 && Double.compare(totalProvision, bank.totalProvision) == 0 && Objects.equals(name, bank.name) && Arrays.equals(accounts, bank.accounts);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(name, totalTransfers, totalProvision);
//        result = 31 * result + Arrays.hashCode(accounts);
//        return result;
//    }
//
//    public Account[] getAccounts() {
//        return accounts;
//    }
//
//
//}
//abstract  class Transaction {
//
//    private long fromId;
//    private long toId;
//    private String description;
//    private String amount;
//
//    public Transaction(long fromId, long toId, String description, String amount) {
//
//        this.fromId = fromId;
//        this.toId = toId;
//        this.description = description;
//        this.amount = amount;
//    }
//
//    public long getFromId() {
//        return fromId;
//    }
//
//    public long getToId() {
//        return toId;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public abstract double getProvision();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Transaction that)) return false;
//        return fromId == that.fromId && toId == that.toId && Objects.equals(description, that.description) && Objects.equals(amount, that.amount);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fromId, toId, description, amount);
//    }
//}
//
