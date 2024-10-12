//package prva;
//
//import java.util.Objects;
//
//public class PhoneContact extends Contact {
//
//    public String phone;
//    public Operator operator;
//
//
//    public PhoneContact(String date, String phone) {
//        super(date);
//        this.phone = phone;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public Operator getOperator() {
//        String[] niza = phone.split("/");
//        niza[0] = String.valueOf(Integer.parseInt(niza[0]));
//        int nov = Integer.parseInt(niza[0].substring(Integer.parseInt(niza[2])));
//
//        if (nov == 0 || nov == 1 || nov == 2) {
//            return Operator.TMOBILE;
//        } else if (nov == 5 || nov == 6) {
//            return Operator.ONE;
//        } else return Operator.VIP;
//    }
//
//    @Override
//    public String getType() {
//        return "Phone";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof PhoneContact that)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(phone, that.phone) && operator == that.operator;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), phone, operator);
//    }
//}
