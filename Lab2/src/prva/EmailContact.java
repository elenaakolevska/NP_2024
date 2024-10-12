//package prva;
//
//import java.util.Objects;
//
//public class EmailContact extends Contact{
//
//    public String email;
//    public String date;
//
//    public EmailContact(String date, String email) {
//        super(date);
//        this.email = email;
//    }
//
//    public String getEmail() {
//        return "Email";
//    }
//
//
//    @Override
//    public String getType() {
//        return email;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof EmailContact that)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(email, that.email) && Objects.equals(date, that.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), email, date);
//    }
//}
