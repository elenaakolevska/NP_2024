package aud9.names;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class NamesTest {
    public static Map<String,Integer> createFromFile(String path) throws FileNotFoundException {
       Map<String,Integer> result = new HashMap<>();
        InputStream is = new FileInputStream(path);
        Scanner sc = new Scanner(is);
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String name = parts[0];
            Integer freq = Integer.parseInt(parts[1]);
            result.put(name,freq);
        }
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> boyNamesMap = createFromFile("C:\\Users\\Elence\\Desktop\\NP\\Auditoriski_vtorKolokvium\\src\\aud9\\names\\boynames.txt");
        Map<String, Integer> girlsNamesMap = createFromFile("C:\\Users\\Elence\\Desktop\\NP\\Auditoriski_vtorKolokvium\\src\\aud9\\names\\girlnames.txt");

        Set<String> allNames = new HashSet<>();
        allNames.addAll(boyNamesMap.keySet());
        allNames.addAll(girlsNamesMap.keySet());

        Map<String, Integer> unisexNames = new HashMap<>();

        allNames.stream()
                .filter(name-> boyNamesMap.containsKey(name) && girlsNamesMap.containsKey(name))
                .forEach(name-> unisexNames.put(name, boyNamesMap.get(name) + girlsNamesMap.get(name)));
//                .forEach(name-> System.out.println(String.format("%s: Male: %d Female: %d Total: %d\n",
//                        name,
//                        boyNamesMap.get(name),
//                        girlsNamesMap.get(name),
//                        boyNamesMap.get(name) + girlsNamesMap.get(name))));

        System.out.println(unisexNames);

        // CESTO NA KOLOKVIUM
        //DA SE SORTIRA MAPATA SPORED VALUE

        unisexNames.entrySet().stream().      // mnozestvo od site parovi
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry-> System.out.println(String.format("%s : %d", entry.getKey(), entry.getValue())));

    }
}
