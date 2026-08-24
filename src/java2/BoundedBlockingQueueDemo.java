package java2;

import java.util.LinkedList;
import java.util.Queue;

class BoundedBlockingQueue<T> {

    private final Queue<T> queue;
    private final int capacity;

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    /**
     * Adds an element.
     * Blocks if the queue is full.
     */
    public synchronized void put(T item) throws InterruptedException {

        if (item == null) {
            throw new NullPointerException("Null elements are not allowed");
        }

        // Queue is full -> wait
        while (queue.size() == capacity) {
            wait();
        }

        queue.offer(item);

        // Notify waiting consumers/producers
        notifyAll();
    }

    /**
     * Removes and returns an element.
     * Blocks if the queue is empty.
     */
    public synchronized T take() throws InterruptedException {

        // Queue is empty -> wait
        while (queue.isEmpty()) {
            wait();
        }

        T item = queue.poll();

        // Notify waiting consumers/producers
        notifyAll();

        return item;
    }

    /**
     * Returns current queue size.
     */
    public synchronized int size() {
        return queue.size();
    }

    /**
     * Returns queue capacity.
     */
    public int capacity() {
        return capacity;
    }
}
public class BoundedBlockingQueueDemo {

    public static void main(String[] args) {

        BoundedBlockingQueue<Integer> queue =
                new BoundedBlockingQueue<>(3);

        Thread producer = new Thread(() -> {

            try {
                for (int i = 1; i <= 10; i++) {

                    System.out.println(
                            "Producer trying to add: " + i);

                    queue.put(i);

                    System.out.println(
                            "Producer added: " + i);

                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {

            try {
                for (int i = 1; i <= 10; i++) {

                    Thread.sleep(1500);

                    int value = queue.take();

                    System.out.println(
                            "Consumer removed: " + value);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
