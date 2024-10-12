//package vtora;
//
//import java.util.Objects;
//
//public class FlatPercentProvisionTransaction extends Transaction {
//
//    private final int centsPerDolar;
//
//    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDolar) {
//        super(fromId, toId, "FlatPercent", amount);
//        this.centsPerDolar = centsPerDolar;
//    }
//
//    public int getCentsPerDolar() {
//        return centsPerDolar;
//    }
//
//    public double parseStringToDouble(String s) {
//        return Double.parseDouble(s.substring(0, s.length() - 1));
//    }
//
//
//    @Override
//    public double getProvision() {
//        return (int) parseStringToDouble(getAmount()) * (centsPerDolar / 100.0);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FlatPercentProvisionTransaction that)) return false;
//        if (!super.equals(o)) return false;
//        return centsPerDolar == that.centsPerDolar;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), centsPerDolar);
//    }
//}
