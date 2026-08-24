package java2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MultiValueMap<K, V> {

    private final Map<K, List<V>> map = new HashMap<>();

    /**
     * Add a value to the list associated with a key.
     */
    public void put(K key, V value) {

        map.computeIfAbsent(key, k -> new ArrayList<>())
           .add(value);
    }

    /**
     * Add multiple values to a key.
     */
    public void putAll(K key, List<V> values) {

        if (values == null || values.isEmpty()) {
            return;
        }

        map.computeIfAbsent(key, k -> new ArrayList<>())
           .addAll(values);
    }

    /**
     * Get all values associated with a key.
     */
    public List<V> get(K key) {

        return map.getOrDefault(
                key,
                Collections.emptyList()
        );
    }

    /**
     * Check whether a key exists.
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * Check whether a particular key-value pair exists.
     */
    public boolean containsEntry(K key, V value) {

        List<V> values = map.get(key);

        return values != null && values.contains(value);
    }

    /**
     * Remove one value from a key.
     */
    public boolean remove(K key, V value) {

        List<V> values = map.get(key);

        if (values == null) {
            return false;
        }

        boolean removed = values.remove(value);

        // Remove the key if no values remain.
        if (values.isEmpty()) {
            map.remove(key);
        }

        return removed;
    }

    /**
     * Remove all values associated with a key.
     */
    public List<V> removeAll(K key) {
        return map.remove(key);
    }

    /**
     * Number of keys.
     */
    public int size() {
        return map.size();
    }

    /**
     * Check whether map contains no keys.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Clear everything.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Return a copy of the underlying map.
     */
    public Map<K, List<V>> asMap() {

        Map<K, List<V>> copy = new HashMap<>();

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {

            copy.put(
                    entry.getKey(),
                    new ArrayList<>(entry.getValue())
            );
        }

        return copy;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}


public class MultiValueMapDemo {

    public static void main(String[] args) {

        MultiValueMap<String, String> studentCourses =
                new MultiValueMap<>();

        // Add individual values
        studentCourses.put("Rahul", "Java");
        studentCourses.put("Rahul", "Spring Boot");
        studentCourses.put("Rahul", "AWS");

        studentCourses.put("Amit", "Docker");
        studentCourses.put("Amit", "Kubernetes");

        // Add multiple values
        studentCourses.putAll(
                "Priya",
                List.of(
                        "Java",
                        "Kafka",
                        "Microservices"
                )
        );

        System.out.println("Student courses:");
        System.out.println(studentCourses);

        System.out.println("\nRahul's courses:");
        System.out.println(studentCourses.get("Rahul"));

        System.out.println("\nDoes Rahul have AWS?");
        System.out.println(
                studentCourses.containsEntry(
                        "Rahul",
                        "AWS"
                )
        );

        System.out.println("\nRemove Spring Boot from Rahul:");
        studentCourses.remove(
                "Rahul",
                "Spring Boot"
        );

        System.out.println(studentCourses);

        System.out.println("\nRemove all courses of Amit:");
        studentCourses.removeAll("Amit");

        System.out.println(studentCourses);
    }
}
