package corejava;

public class SynchronizedPerformance {

    private static final int THREADS = 4;
    private static final int ITERATIONS = 5_000_000;

    // =========================================================
    // 1. synchronized method
    // =========================================================

    static class SynchronizedMethodCounter {

        private long count = 0;

        public synchronized void increment() {
            count++;
        }

        public long getCount() {
            return count;
        }
    }

    // =========================================================
    // 2. synchronized block
    // =========================================================

    static class SynchronizedBlockCounter {

        private long count = 0;

        public void increment() {

            synchronized (this) {
                count++;
            }
        }

        public long getCount() {
            return count;
        }
    }

    // =========================================================
    // Benchmark synchronized method
    // =========================================================

    private static long testSynchronizedMethod()
            throws InterruptedException {

        SynchronizedMethodCounter counter =
                new SynchronizedMethodCounter();

        Thread[] threads = new Thread[THREADS];

        long start = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < ITERATIONS; j++) {
                    counter.increment();
                }

            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.nanoTime();

        System.out.println(
                "Synchronized Method Count = "
                        + counter.getCount()
        );

        return end - start;
    }

    // =========================================================
    // Benchmark synchronized block
    // =========================================================

    private static long testSynchronizedBlock()
            throws InterruptedException {

        SynchronizedBlockCounter counter =
                new SynchronizedBlockCounter();

        Thread[] threads = new Thread[THREADS];

        long start = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < ITERATIONS; j++) {
                    counter.increment();
                }

            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.nanoTime();

        System.out.println(
                "Synchronized Block Count = "
                        + counter.getCount()
        );

        return end - start;
    }

    // =========================================================
    // Main
    // =========================================================

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Threads     : " + THREADS);
        System.out.println("Iterations  : " + ITERATIONS);
        System.out.println();

        // Warm-up
        for (int i = 0; i < 3; i++) {
            testSynchronizedMethod();
            testSynchronizedBlock();
        }

        System.out.println("\n=== Actual Benchmark ===");

        long methodTime =
                testSynchronizedMethod();

        long blockTime =
                testSynchronizedBlock();

        System.out.println();

        System.out.printf(
                "Synchronized Method : %.2f ms%n",
                methodTime / 1_000_000.0
        );

        System.out.printf(
                "Synchronized Block  : %.2f ms%n",
                blockTime / 1_000_000.0
        );

        System.out.printf(
                "Block/Method ratio  : %.2fx%n",
                (double) blockTime / methodTime
        );
    }
}
