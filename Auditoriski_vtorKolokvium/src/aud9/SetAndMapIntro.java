package aud9;

import java.util.*;

public class SetAndMapIntro {
    public static void main(String[] args) {
        Set<Integer> treeIntSet = new TreeSet<>(Comparator.reverseOrder()); // elementite mora da se comparable

        // kompleksnost za pristap O(logn)
        // za iteriranje O(n logn)
        // dodavanje O(logn)
        // brisenje O(n logn)

        // treeSet koristime vo zadaci vo koi se bara da se cuvaat unikatni elementi
        // i istite da se sortirani spored nekoj komparator

        for (int i = 1; i <= 10; i++) {
            treeIntSet.add(i);
        }

       // System.out.println(treeIntSet);


        //najdenostavna vremenska kompleksnost
        // koristime hashSet ako se bara vnesuvanje elementi so kompleksnost O(n)
        // nema duplikati
        Set<Integer> hashIntSet = new HashSet<>(); // ne e garantirano zadrzuvanje na redosled
        for (int i = 1; i <= 10; i++) {
            hashIntSet.add(i);
            hashIntSet.add(i);
        }

        System.out.println(hashIntSet);

        //LinkedHashSet
        // za da se zacuva redosledot na vnesuvanje
        // ista kompleksnost kako HashSet
        Set<String> linkedStringHashSet = new LinkedHashSet<>();
        linkedStringHashSet.add("FINKI");
        linkedStringHashSet.add("finki");
        linkedStringHashSet.add("NP");
        linkedStringHashSet.add("Napredno");

        System.out.println(linkedStringHashSet);
    }
}
