package corejava;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreConnectionPool {

    private final Semaphore semaphore;

    public SemaphoreConnectionPool(int maxConnections) {
        // Fair semaphore prevents thread starvation
        this.semaphore = new Semaphore(maxConnections, true);
    }

    public Connection acquire(long timeout, TimeUnit unit)
            throws InterruptedException {

        // Wait for an available connection
        if (!semaphore.tryAcquire(timeout, unit)) {
            throw new RuntimeException(
                    "Could not acquire database connection within timeout");
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " acquired connection. Available: "
                        + semaphore.availablePermits());

        return new Connection();
    }

    public void release(Connection connection) {

        if (connection != null) {
            connection.close();

            semaphore.release();

            System.out.println(
                    Thread.currentThread().getName()
                            + " released connection. Available: "
                            + semaphore.availablePermits());
        }
    }

    // Simulated database connection
    static class Connection {

        public void execute(String query) throws InterruptedException {

            System.out.println(
                    Thread.currentThread().getName()
                            + " executing: " + query);

            Thread.sleep(2000);

            System.out.println(
                    Thread.currentThread().getName()
                            + " query completed");
        }

        public void close() {
            System.out.println(
                    Thread.currentThread().getName()
                            + " closing connection");
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        int maxConnections = 3;

        SemaphoreConnectionPool pool =
                new SemaphoreConnectionPool(maxConnections);

        // Simulate 10 concurrent requests
        for (int i = 1; i <= 10; i++) {

            final int requestId = i;

            Thread thread = new Thread(() -> {

                Connection connection = null;

                try {

                    connection = pool.acquire(
                            5,
                            TimeUnit.SECONDS
                    );

                    connection.execute(
                            "SELECT * FROM orders WHERE id = "
                                    + requestId
                    );

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " interrupted");

                } finally {

                    pool.release(connection);
                }

            }, "Request-" + requestId);

            thread.start();
        }
    }
}
