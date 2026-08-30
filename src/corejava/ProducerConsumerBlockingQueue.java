package corejava;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 20; i++) {
                try {
                    queue.put(i);

                    System.out.println(
                        "Produced: " + i +
                        " | Queue size: " + queue.size()
                    );

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
                    Integer value = queue.take();

                    System.out.println(
                        "Consumed: " + value +
                        " | Queue size: " + queue.size()
                    );

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
