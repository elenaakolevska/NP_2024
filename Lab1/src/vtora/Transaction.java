//package vtora;
//
//import java.util.Objects;
//
//abstract public class Transaction {
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
