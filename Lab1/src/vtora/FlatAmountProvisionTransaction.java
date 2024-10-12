//package vtora;
//
//import java.util.Objects;
//
//public class FlatAmountProvisionTransaction extends Transaction{
//
//    private String flatProvision;
//
//    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision) {
//        super(fromId, toId, "FlatAmount", amount);
//        this.flatProvision = flatProvision;
//    }
//
//    public double parseStringToDouble(String s){
//       return  Double.parseDouble(s.substring(0,s.length()-1));
//    }
//    @Override
//    public double getProvision() {
//        return parseStringToDouble(flatProvision);
//    }
//
//    public String getFlatProvision() {
//        return flatProvision;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FlatAmountProvisionTransaction that)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(flatProvision, that.flatProvision);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), flatProvision);
//    }
//}
