package java2;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapCacheDemo {

    static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Map<User, String> cache = new WeakHashMap<>();

        // Strong references to keys
        User user1 = new User(1, "Amit");
        User user2 = new User(2, "Rahul");

        cache.put(user1, "User Profile - Amit");
        cache.put(user2, "User Profile - Rahul");

        System.out.println("Initial cache:");
        printCache(cache);

        // Remove strong reference to user1
        user1 = null;

        System.out.println("\nAfter removing strong reference to user1:");
        System.out.println("Before GC:");
        printCache(cache);

        // Request garbage collection
        System.gc();

        // Give GC some time
        Thread.sleep(1000);

        System.out.println("\nAfter GC:");
        printCache(cache);

        // user2 is still strongly referenced
        System.out.println("\nuser2 is still available:");
        System.out.println(user2);

        System.out.println("\nFinal cache size: " + cache.size());
    }

    private static void printCache(Map<User, String> cache) {
        cache.forEach((key, value) ->
                System.out.println(key + " -> " + value));
    }
}
