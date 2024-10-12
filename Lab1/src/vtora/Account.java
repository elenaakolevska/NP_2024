//package vtora;
//
//import java.io.BufferedWriter;
//import java.util.Objects;
//import java.util.Random;
//
//public class Account {
//
//    private String name;
//    private long id;
//    private String balance;
//
//    public Account(String name, String balance) {
//        this.name = name;
//        Random random = new Random();
//        this.id = random.nextLong();
//        this.balance = balance;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public String getBalance() {
//        return balance;
//    }
//
//    public void setBalance(String balance) {
//        this.balance = balance;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Account account)) return false;
//        return id == account.id && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, id, balance);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Name:").append(name).append("\n\n");
//        sb.append("Balance:").append(balance).append("\n\n");
//
//        return sb.toString();
//    }
//}
