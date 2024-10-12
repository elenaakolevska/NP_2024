//package prva;
//
//import java.util.Arrays;
//
//public class Faculty {
//
//    String name;
//    Student[] students;
//
//    public Faculty(String name, Student[] students) {
//        this.name = name;
//        this.students = students;
//    }
//
//    public int countStudentsFromCity(String cityName){
//        int counter=0;
//
//        for (Student s: students) {
//            if(s.city.equals(cityName)){
//                counter++;
//            }
//        }
//
//        return counter;
//    }
//
//    public Student getStudent(long index){
//        for (Student s:students) {
//            if(s.index == index) return s;
//        }
//        return null;
//    }
//
//    public double getAverageNumberOfContacts(){
//        double average;
//        int suma=0;
//        int counter=0;
//        for (Student s : students){
//            suma+= s.contacts.length;
//            counter++;
//        }
//        average=suma/counter;
//        return average;
//    }
//
//    public Student getStudentWithMostContacts(){
//        Student max=students[0];
//
//        for (Student s:students) {
//            if(s.contacts.length > max.contacts.length){
//                max = s;
//            }
//            if(s.contacts.length == max.contacts.length){
//                if(s.index > max.index){
//                    max.index = s.index;
//                }
//            }
//
//        }
//        return max;
//    }
//
//    @Override
//    public String toString() {
//        return "Faculty{" +
//                "fakultet='" + name + '\'' +
//                ", students=" + Arrays.toString(students) +
//                '}';
//    }
//}
