package corejava;

/*
                 Bounded Buffer
              capacity = 5
                    |
        +-----------+-----------+
        |                       |
        v                       v
    Producer                Consumer
        |                       |
        | put()                 | take()
        v                       v
   Buffer Full?             Buffer Empty?
      |                           |
     YES                         YES
      |                           |
   wait()                       wait()
      |                           |
      +----------+    +----------+
                 |    |
                 v    v
              notifyAll()
*/
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {

    static class BoundedBuffer<T> {

        private final Queue<T> queue = new LinkedList<>();
        private final int capacity;

        public BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        // Producer
        public synchronized void put(T item)
                throws InterruptedException {

            // Buffer full → producer waits
            while (queue.size() == capacity) {
                wait();
            }

            queue.add(item);

            System.out.println(
                "Produced: " + item +
                " | Buffer size: " + queue.size()
            );

            // Notify waiting consumers/producers
            notifyAll();
        }

        // Consumer
        public synchronized T take()
                throws InterruptedException {

            // Buffer empty → consumer waits
            while (queue.isEmpty()) {
                wait();
            }

            T item = queue.poll();

            System.out.println(
                "Consumed: " + item +
                " | Buffer size: " + queue.size()
            );

            // Notify waiting producers/consumers
            notifyAll();

            return item;
        }
    }

    public static void main(String[] args) {

        BoundedBuffer<Integer> buffer =
                new BoundedBuffer<>(5);

        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 20; i++) {
                try {
                    buffer.put(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        }, "Producer");

        Thread consumer = new Thread(() -> {

            for (int i = 1; i <= 20; i++) {
                try {
                    buffer.take();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        }, "Consumer");

        producer.start();
        consumer.start();
    }
}
