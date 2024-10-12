package prva;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


enum Operator {
    VIP,
    ONE,
    TMOBILE
}

abstract  class Contact {
    public String date;

    public Contact(String date) {
        this.date = date;
    }

    public boolean isNewerThan(Contact c){
        return this.getDateAsInt() > c.getDateAsInt();
    }
    public int getDateAsInt(){
        String[] datum = this.date.split("-");
        return Integer.parseInt(datum[0])*10000 + Integer.parseInt(datum
                [1])*100 + Integer.parseInt(datum[2]);
    }

    abstract public String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(date, contact.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}

class Student {
    String firstName;
    String lastName;
    String city;
    int age;
    long index;

    Contact[] contacts;
    int n;


    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;

        this.contacts = new Contact[0];
        this.n = 0;
    }

    public void addEmailContact(String date, String email){
        contacts = Arrays.copyOf(contacts, n+1);
        contacts[n]= new EmailContact(date, email);
        n++;
    }

    public void addPhoneContact(String date, String phone){
        contacts = Arrays.copyOf(contacts, n+1);
        contacts[n]= new PhoneContact(date, phone);
        n++;
    }

    public Contact[] getEmailContacts(){
        Contact[] niza;
        int i=0;

        for (Contact c: contacts) {
            if(c.getType().equals("Email")){
                i++;
            }
        }

        niza = new Contact[i];
        i=0;

        for (Contact c:contacts) {
            if(c.getType().equals("Email")){
                niza[i] = c;
                i++;
            }
        }
        return niza;
    }

    public Contact[] getPhoneContacts(){
        Contact[] nova;
        int i=0;

        for (Contact c: contacts) {
            if(c.getType().equals("Phone")){
                i++;
            }
        }

        nova = new Contact[i];
        i=0;
        for (Contact c: contacts) {
            if(c.getType().equals("Phone")){
                nova[i] = c;
                i++;
            }
        }
        return nova;

    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public long getIndex() {
        return index;
    }

    public Contact getLatestContact(){

        if(contacts.length==0){
            return null;
        }
        Contact latest =contacts[0];

        for (int i = 1; i < contacts.length; i++) {
            if(contacts[i].isNewerThan(latest)){
                latest=contacts[i];
            }
        }
        return latest;
    }

    @Override
    public String toString() {
        return "{\"ime\":\"" + firstName + "\", \"prezime\":\"" + lastName + "\", \"vozrast\":" + age +
                ", \"grad\":\"" + city + "\", \"indeks\":" + index +
                ", \"telefonskiKontakti\":" + Arrays.toString(getPhoneContacts()) +
                ", \"emailKontakti\":" + Arrays.toString(getEmailContacts()) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age && index == student.index && n == student.n && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(city, student.city) && Arrays.equals(contacts, student.contacts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, lastName, city, age, index, n);
        result = 31 * result + Arrays.hashCode(contacts);
        return result;
    }
}
class Faculty {

    String name;
    Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName){
        int counter=0;

        for (Student s: students) {
            if(s.city.equals(cityName)){
                counter++;
            }
        }

        return counter;
    }

    public Student getStudent(long index){
        for (Student s:students) {
            if(s.index == index) return s;
        }
        return null;
    }

    public double getAverageNumberOfContacts(){
        double average;
        int suma=0;
        int counter=0;
        for (Student s : students){
            suma+= s.contacts.length;
            counter++;
        }
        average=(double)suma/counter;
        return average;
    }

    public Student getStudentWithMostContacts(){
        Student max=students[0];

        for (Student s:students) {
            if(s.contacts.length > max.contacts.length){
                max = s;
            }
            else if(s.contacts.length == max.contacts.length){
                if(s.index > max.index){
                    max =s;
                }
            }

        }
        return max;
    }

    @Override
    public String toString() {
        return "{\"fakultet\":\"" + name + "\", \"studenti\":" + Arrays.toString(students) + "}";
    }

}
class EmailContact extends Contact{

    public String email;
    public String date;

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String getType() {
        return "Email";
    }

    @Override
    public String toString() {
        return
                "\"" + email + "\"" ;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailContact)) return false;
        EmailContact that = (EmailContact) o;
        if (!super.equals(o)) return false;
        return Objects.equals(email, that.email) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, date);
    }
}
class PhoneContact extends Contact {

    public String phone;
    public Operator operator;


    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public Operator getOperator() {
        String[] niza = phone.split("/");
        int nov = Integer.parseInt(niza[0].substring(2));

        if (nov == 0 || nov == 1 || nov == 2) {
            return Operator.TMOBILE;
        } else if (nov == 5 || nov == 6) {
            return Operator.ONE;
        } else return Operator.VIP;
    }

    @Override
    public String getType() {
        return "Phone";
    }

    @Override
    public String toString() {
        return "\"" + phone + "\"" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneContact)) return false;
        PhoneContact that = (PhoneContact) o;
        if (!super.equals(o)) return false;
        return Objects.equals(phone, that.phone) && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone, operator);
    }
}
public class ContactsTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}
