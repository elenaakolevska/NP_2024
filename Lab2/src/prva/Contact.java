package prva;

import java.util.Objects;

//abstract public class Contact {
//    public String date;
//
//    public Contact(String date) {
//        this.date = date;
//    }
//
//    public boolean isNewerThan(Contact c){
//        return this.getDateAsInt() > c.getDateAsInt();
//    }
//    public int getDateAsInt(){
//        String[] datum = this.date.split("-");
//        return Integer.parseInt(datum[0])*10000 + Integer.parseInt(datum
//        [1])*100 + Integer.parseInt(datum[2]);
//    }
//
//    abstract public String getType();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Contact contact)) return false;
//        return Objects.equals(date, contact.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(date);
//    }
//}
