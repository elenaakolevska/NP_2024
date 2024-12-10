package aud8;

import java.util.Collection;
import java.util.stream.Stream;

/*
Да се имплементира следниот метод коj го враќа бројот на појавување на стрингот str во колекцијата од колекција од стрингови:

public static int count(Collection<Collection<String>> c, String str)
Да претпоставиме дека Collection c содржи N колекции и дека секоја од овие колекции содржи N објекти. Кое е времето на извршување на вашиот метод?

Да претпоставиме дека е потребно 2 милисекунди да се изврши за N = 100. Колку ќе биде времето на извршување кога N = 300?
 */
public class CountOccurrencesTest {
    public static int count(Collection<Collection<String>> c, String str) {
        int counter = 0;
        for (Collection<String> collection : c) {
            for (String element : collection) {
                if (element.equalsIgnoreCase(str)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int count2(Collection<Collection<String>> c, String str) {
        return (int) c.stream()
                .flatMap(col -> col.stream())
                .filter(string -> string.equalsIgnoreCase(str))
                .count();
    }


    public static void main(String[] args) {
        // Your test cases can be added here
    }
}
