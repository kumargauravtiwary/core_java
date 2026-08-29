package java2;
public class CustomUncaughtExceptionHandler {

    public static void main(String[] args) {

        Thread.UncaughtExceptionHandler handler =
                new GlobalExceptionHandler();

        Thread worker = new Thread(() -> {

            System.out.println(
                    Thread.currentThread().getName() +
                    " started"
            );

            // Simulate unexpected failure
            throw new RuntimeException(
                    "Database connection failed!"
            );

        }, "payment-worker");

        // Register handler for this thread
        worker.setUncaughtExceptionHandler(handler);

        worker.start();

        System.out.println("Main thread continues...");
    }

    static class GlobalExceptionHandler
            implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(
                Thread thread,
                Throwable throwable) {

            System.err.println(
                    "Unhandled exception in thread: "
                    + thread.getName()
            );

            System.err.println(
                    "Exception: "
                    + throwable.getClass().getName()
            );

            System.err.println(
                    "Message: "
                    + throwable.getMessage()
            );

            // In production:
            // logger.error("Unhandled exception", throwable);
            // send alert to monitoring system
            // increment failure metric
        }
    }
}