package corejava;
/*
Insertion order:
50 → 20 → 40 → 10 → 30

       │
       ├── HashMap
       │      ↓
       │   unspecified order
       │
       ├── LinkedHashMap
       │      ↓
       │   50 → 20 → 40 → 10 → 30
       │
       └── TreeMap
              ↓
          10 → 20 → 30 → 40 → 50
*/
import java.util.*;

public class MapOrderingDemo {

    public static void main(String[] args) {

        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        // Insert in this order
        int[] keys = {50, 20, 40, 10, 30};

        for (int key : keys) {
            hashMap.put(key, "Value-" + key);
            linkedHashMap.put(key, "Value-" + key);
            treeMap.put(key, "Value-" + key);
        }

        System.out.println("HashMap:");
        hashMap.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        System.out.println("\nLinkedHashMap:");
        linkedHashMap.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        System.out.println("\nTreeMap:");
        treeMap.forEach((k, v) ->
                System.out.println(k + " -> " + v));
    }
}
