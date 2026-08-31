package corejava;

/*
                 CountDownLatch(3)
                       │
          ┌────────────┼────────────┐
          │            │            │
       Worker 1      Worker 2     Worker 3
       Database       Cache       MQ
          │            │            │
      countDown()   countDown()  countDown()
          │            │            │
          └────────────┼────────────┘
                       │
                     count=0
                       │
                       ▼
                 Main thread
                  continues
*/
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        int numberOfWorkers = 3;

        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        System.out.println("Application starting...");

        Thread databaseWorker = new Thread(
                new Worker("Database", 2000, latch));

        Thread cacheWorker = new Thread(
                new Worker("Cache", 3000, latch));

        Thread messageQueueWorker = new Thread(
                new Worker("Message Queue", 1500, latch));

        databaseWorker.start();
        cacheWorker.start();
        messageQueueWorker.start();

        System.out.println("Main thread waiting for all workers...");

        // Wait until count reaches zero
        latch.await();

        System.out.println("\nAll workers completed!");
        System.out.println("Application is ready to accept requests.");
    }

    static class Worker implements Runnable {

        private final String workerName;
        private final int processingTime;
        private final CountDownLatch latch;

        Worker(String workerName,
               int processingTime,
               CountDownLatch latch) {

            this.workerName = workerName;
            this.processingTime = processingTime;
            this.latch = latch;
        }

        @Override
        public void run() {

            try {
                System.out.println(workerName + " initialization started.");

                Thread.sleep(processingTime);

                System.out.println(workerName + " initialization completed.");

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(workerName + " was interrupted.");

            } finally {

                // Always decrement the latch
                latch.countDown();
            }
        }
    }
}
