//package prva;
//
//import java.util.Arrays;
//import java.util.Objects;
//
//public class Student {
//    String firstName;
//    String lastName;
//    String city;
//    int age;
//    long index;
//
//    Contact[] contacts;
//    int n;
//
//
//    public Student(String firstName, String lastName, String city, int age, long index) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.city = city;
//        this.age = age;
//        this.index = index;
//
//        this.contacts = new Contact[0];
//        this.n = 0;
//    }
//
//    public void addEmailContact(String date, String email){
//        contacts = Arrays.copyOf(contacts, n+1);
//        contacts[n]= new EmailContact(date, email);
//        n++;
//    }
//
//    public void addPhoneContact(String date, String phone){
//        contacts = Arrays.copyOf(contacts, n+1);
//        contacts[n]= new EmailContact(date, phone);
//        n++;
//    }
//
//    public Contact[] getEmailContacts(){
//        Contact[] niza;
//        int i=0;
//
//        for (Contact c: contacts) {
//            if(c.getType().equals("Email")){
//                i++;
//            }
//        }
//
//        niza = new Contact[i];
//        i=0;
//
//        for (Contact c:contacts) {
//            if(c.getType().equals("Email")){
//                niza[i] = c;
//                i++;
//            }
//        }
//        return niza;
//    }
//
//    public Contact[] getPhoneContacts(){
//        Contact[] nova;
//        int i=0;
//
//        for (Contact c: contacts) {
//            if(c.getType().equals("Phone")){
//                i++;
//            }
//        }
//
//        nova = new Contact[i];
//        i=0;
//        for (Contact c: contacts) {
//            if(c.getType().equals("Phone")){
//                nova[i] = c;
//                i++;
//            }
//        }
//        return nova;
//
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//
//    public long getIndex() {
//        return index;
//    }
//
//    public Contact getLatestContact(){
//        int latest =0;
//
//        for (int i = 0; i < contacts.length; i++) {
//            if(contacts[i].isNewerThan(contacts[latest])){
//                latest=i;
//            }
//        }
//        return contacts[latest];
//    }
//
//    @Override
//    public String toString() {
//        return "fakultet{" +
//                "ime='" + firstName + '\'' +
//                ", prezime:" + lastName + '\'' +
//                ", vozrast:" + age +
//                ", grad:" + city + '\'' +
//                ", indeks:" + index +
//                ", telefonskiKontakti:" + Arrays.toString(getPhoneContacts()) +
//                ", telefonskiKontakti:" + Arrays.toString(getEmailContacts()) +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student student)) return false;
//        return age == student.age && index == student.index && n == student.n && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(city, student.city) && Arrays.equals(contacts, student.contacts);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(firstName, lastName, city, age, index, n);
//        result = 31 * result + Arrays.hashCode(contacts);
//        return result;
//    }
//}
