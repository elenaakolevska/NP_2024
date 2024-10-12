package av4.vtora;

import java.io.*;
import java.util.Comparator;

class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person (String line){
        String[] parts = line.split("\\s+");
        this.name=parts[0];
        this.age= Integer.parseInt(parts[1]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}

public class OldestPersonTest {

    public static Person find(InputStream is){
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //Kristijan 25
         return br.lines().
                 map(line -> new Person(line))// od string mapirame vo Person
                 .max(Comparator.naturalOrder())  //kako so e definirano vo compareTo taka sporeduvaj i tuka
                 .orElse(new Person("Elena", 21));

    }


    public static void main(String[] args) {

        try {
            InputStream isFromFile= new FileInputStream(new File("C:\\Users\\Elence\\Desktop\\NP\\Auditoriski\\src\\av4\\vtora\\people.txt"));
            System.out.println(OldestPersonTest.find(isFromFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
