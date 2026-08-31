package corejava;
/*
                  PHASE 1
              Parallel computation
                     │
       ┌─────────────┼─────────────┐
       ▼             ▼             ▼
   Worker 1      Worker 2      Worker 3 ... Worker 4
       │             │             │
       └─────────────┼─────────────┘
                     │
                CYCLIC BARRIER
                     │
              All workers arrive
                     │
                     ▼
                  PHASE 2
              Parallel computation
                     │
       ┌─────────────┼─────────────┐
       ▼             ▼             ▼
   Worker 1      Worker 2      Worker 3 ... Worker 4
       │             │             │
       └─────────────┼─────────────┘
                     │
                CYCLIC BARRIER
                     │
                     ▼
                  PHASE 3
              Parallel computation
                     │
       ┌─────────────┼─────────────┐
       ▼             ▼             ▼
   Worker 1      Worker 2      Worker 3 ... Worker 4
                     │
                     ▼
                CYCLIC BARRIER
                     │
                     ▼
             ALL PHASES COMPLETE
*/
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final int WORKERS = 4;
    private static final int PHASES = 3;

    public static void main(String[] args) throws InterruptedException {

        // Action executed when the last worker reaches the barrier
        CyclicBarrier barrier = new CyclicBarrier(
                WORKERS,
                () -> System.out.println(
                        "\n>>> All workers completed current phase <<<\n")
        );

        Thread[] workers = new Thread[WORKERS];

        for (int i = 0; i < WORKERS; i++) {

            int workerId = i + 1;

            workers[i] = new Thread(
                    new Worker(workerId, barrier),
                    "Worker-" + workerId
            );

            workers[i].start();
        }

        // Wait for all workers
        for (Thread worker : workers) {
            worker.join();
        }

        System.out.println("All phases completed.");
    }

    static class Worker implements Runnable {

        private final int workerId;
        private final CyclicBarrier barrier;

        Worker(int workerId, CyclicBarrier barrier) {
            this.workerId = workerId;
            this.barrier = barrier;
        }

        @Override
        public void run() {

            try {

                // -------------------------
                // PHASE 1
                // -------------------------
                System.out.println(
                        Thread.currentThread().getName()
                                + " starting Phase 1");

                performComputation("Phase 1");

                System.out.println(
                        Thread.currentThread().getName()
                                + " completed Phase 1");

                barrier.await();

                // -------------------------
                // PHASE 2
                // -------------------------
                System.out.println(
                        Thread.currentThread().getName()
                                + " starting Phase 2");

                performComputation("Phase 2");

                System.out.println(
                        Thread.currentThread().getName()
                                + " completed Phase 2");

                barrier.await();

                // -------------------------
                // PHASE 3
                // -------------------------
                System.out.println(
                        Thread.currentThread().getName()
                                + " starting Phase 3");

                performComputation("Phase 3");

                System.out.println(
                        Thread.currentThread().getName()
                                + " completed Phase 3");

                barrier.await();

                System.out.println(
                        Thread.currentThread().getName()
                                + " finished all phases.");

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            } catch (BrokenBarrierException e) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " barrier was broken.");
            }
        }

        private void performComputation(String phase)
                throws InterruptedException {

            // Simulate different computation times
            Thread.sleep(
                    500 + (long) (Math.random() * 1500)
            );
        }
    }
}
