package corejava;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVsSynchronizedCounter {

    private static final int THREADS = 8;
    private static final int ITERATIONS = 2_000_000;

    // ============================================================
    // 1. AtomicInteger Counter
    // ============================================================

    static class AtomicCounter {

        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public int get() {
            return count.get();
        }
    }

    // ============================================================
    // 2. Synchronized Counter
    // ============================================================

    static class SynchronizedCounter {

        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int get() {
            return count;
        }
    }

    // ============================================================
    // Test AtomicInteger
    // ============================================================

    private static long testAtomicCounter()
            throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

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
                "Atomic Counter     = " + counter.get()
        );

        return end - start;
    }

    // ============================================================
    // Test Synchronized Counter
    // ============================================================

    private static long testSynchronizedCounter()
            throws InterruptedException {

        SynchronizedCounter counter =
                new SynchronizedCounter();

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
                "Synchronized Counter = " + counter.get()
        );

        return end - start;
    }

    // ============================================================
    // Main
    // ============================================================

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Threads    : " + THREADS);
        System.out.println("Iterations : " + ITERATIONS);
        System.out.println();

        // --------------------------------------------------------
        // Warm-up
        // --------------------------------------------------------

        System.out.println("Warming up...");

        for (int i = 0; i < 3; i++) {
            testAtomicCounter();
            testSynchronizedCounter();
        }

        // --------------------------------------------------------
        // Benchmark
        // --------------------------------------------------------

        System.out.println("\n=== Benchmark ===");

        long atomicTime =
                testAtomicCounter();

        long synchronizedTime =
                testSynchronizedCounter();

        double atomicMs =
                atomicTime / 1_000_000.0;

        double synchronizedMs =
                synchronizedTime / 1_000_000.0;

        System.out.printf(
                "%nAtomicInteger       : %.2f ms%n",
                atomicMs
        );

        System.out.printf(
                "Synchronized        : %.2f ms%n",
                synchronizedMs
        );

        System.out.printf(
                "Synchronized/Atomic : %.2fx%n",
                synchronizedMs / atomicMs
        );
    }
}
