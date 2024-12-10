package aud9;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapIntro {
    public static void main(String[] args) {

        // MAPA KORISTIME ZA BROENJE, GRUPIRANJE



        // klucot mora da e comparable kaj treeMap
        // izbegnuva duplikat klucevi
        // mapata e sortirana spored klucot
        // O(logn) za dodavanje,
        // O(logn) za contains,
        // O(nlogn) za iteriranje
        Map<String, String> treeMap = new TreeMap<>();

        treeMap.put("FINKI","FINKI");
        treeMap.put("FinFI", "Finki");
        treeMap.put("NP", "napredno programiranje");
        treeMap.put("F", "Fakultet");
        treeMap.put("I", "Informaticki");

        System.out.println(treeMap);

        //HashMap
        // O(1) dodavanje, contains
        // O(n) iteriranje

        // elementite so se klucevi moraat da imaat override na hash metodot
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("FINKI","FINKI");
        hashMap.put("FinFI", "Finki");
        hashMap.put("NP", "napredno programiranje");
        hashMap.put("F", "Fakultet");
        hashMap.put("I", "Informaticki");

        System.out.println(hashMap);

        Map<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("FINKI","FINKI");
        linkedHashMap.put("FinFI", "Finki");
        linkedHashMap.put("NP", "napredno programiranje");
        linkedHashMap.put("F", "Fakultet");
        linkedHashMap.put("I", "Informaticki");

        System.out.println(linkedHashMap);
    }
}
