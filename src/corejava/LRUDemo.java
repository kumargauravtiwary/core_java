package corejava;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> {

    private final int capacity;

    private final LinkedHashMap<K, V> cache;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.capacity = capacity;

        // true = access-order
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<K, V> eldest) {

                return size() > LRUCache.this.capacity;
            }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void display() {
        System.out.println(cache);
    }

    public int size() {
        return cache.size();
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
}

public class LRUDemo {

    public static void main(String[] args) {

        LRUCache<Integer, String> cache =
                new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println("Initial cache:");
        cache.display();

        // Access key 1
        cache.get(1);

        System.out.println("After accessing key 1:");
        cache.display();

        // Add key 4
        cache.put(4, "D");

        System.out.println("After adding key 4:");
        cache.display();

        System.out.println("Contains key 2: "
                + cache.containsKey(2));

        System.out.println("Contains key 1: "
                + cache.containsKey(1));
    }
}
