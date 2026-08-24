package corejava;

public class CustomHashMap<K, V> {

    // Node represents one key-value pair
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;

    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    private static final float LOAD_FACTOR = 0.75f;

    public CustomHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    // =========================
    // PUT
    // =========================
    public void put(K key, V value) {

        // Check whether resizing is required
        if ((size + 1) > buckets.length * LOAD_FACTOR) {
            resize();
        }

        int index = getBucketIndex(key);

        Node<K, V> current = buckets[index];

        // Check if key already exists
        while (current != null) {

            if (keysEqual(current.key, key)) {

                // Update existing value
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Insert new node at beginning of linked list
        Node<K, V> newNode = new Node<>(key, value);

        newNode.next = buckets[index];

        buckets[index] = newNode;

        size++;
    }

    // =========================
    // GET
    // =========================
    public V get(K key) {

        int index = getBucketIndex(key);

        Node<K, V> current = buckets[index];

        while (current != null) {

            if (keysEqual(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // =========================
    // REMOVE
    // =========================
    public V remove(K key) {

        int index = getBucketIndex(key);

        Node<K, V> current = buckets[index];

        Node<K, V> previous = null;

        while (current != null) {

            if (keysEqual(current.key, key)) {

                // Removing first node
                if (previous == null) {
                    buckets[index] = current.next;
                }
                // Removing middle/last node
                else {
                    previous.next = current.next;
                }

                size--;

                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    // =========================
    // HASH FUNCTION
    // =========================
    private int getBucketIndex(K key) {

        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();

        // Spread the hash bits
        hash = hash ^ (hash >>> 16);

        return (hash & 0x7fffffff) % buckets.length;
    }

    // =========================
    // KEY COMPARISON
    // =========================
    private boolean keysEqual(K key1, K key2) {

        if (key1 == key2) {
            return true;
        }

        if (key1 == null || key2 == null) {
            return false;
        }

        return key1.equals(key2);
    }

    // =========================
    // RESIZE
    // =========================
    private void resize() {

        Node<K, V>[] oldBuckets = buckets;

        buckets = new Node[oldBuckets.length * 2];

        size = 0;

        // Reinsert all elements
        for (Node<K, V> head : oldBuckets) {

            Node<K, V> current = head;

            while (current != null) {

                put(current.key, current.value);

                current = current.next;
            }
        }
    }

    // =========================
    // SIZE
    // =========================
    public int size() {
        return size;
    }

    // =========================
    // DISPLAY
    // =========================
    public void display() {

        for (int i = 0; i < buckets.length; i++) {

            Node<K, V> current = buckets[i];

            if (current != null) {

                System.out.print("Bucket " + i + ": ");

                while (current != null) {

                    System.out.print(
                            "[" + current.key + "=" + current.value + "] -> "
                    );

                    current = current.next;
                }

                System.out.println("null");
            }
        }
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        CustomHashMap<String, Integer> map =
                new CustomHashMap<>();

        map.put("Java", 100);
        map.put("Spring", 200);
        map.put("Kafka", 300);

        System.out.println("Java = " + map.get("Java"));
        System.out.println("Spring = " + map.get("Spring"));

        // Update existing key
        map.put("Java", 500);

        System.out.println("Java after update = " + map.get("Java"));

        // Remove
        System.out.println("Removed = " + map.remove("Kafka"));

        System.out.println("Kafka = " + map.get("Kafka"));

        System.out.println("Size = " + map.size());

        map.display();
    }
}
