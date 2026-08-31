package corejava;

import java.util.LinkedList;
import java.util.Queue;

public class CustomThreadPool {

    private final Queue<Runnable> taskQueue = new LinkedList<>();
    private final Worker[] workers;

    private volatile boolean shutdown = false;

    public CustomThreadPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be > 0");
        }

        workers = new Worker[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker("Worker-" + i);
            workers[i].start();
        }
    }

    // Submit a task
    public void submit(Runnable task) {

        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        synchronized (taskQueue) {

            if (shutdown) {
                throw new IllegalStateException(
                        "Thread pool has been shut down"
                );
            }

            taskQueue.offer(task);

            // Wake up a waiting worker
            taskQueue.notify();
        }
    }

    // Graceful shutdown
    public void shutdown() {

        synchronized (taskQueue) {
            shutdown = true;

            // Wake up all workers so they can exit
            taskQueue.notifyAll();
        }
    }

    // Worker thread
    private class Worker extends Thread {

        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {

            while (true) {

                Runnable task;

                synchronized (taskQueue) {

                    // Wait until there is work
                    while (taskQueue.isEmpty() && !shutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // Exit only when shutdown AND no tasks remain
                    if (taskQueue.isEmpty() && shutdown) {
                        return;
                    }

                    task = taskQueue.poll();
                }

                // Execute outside synchronized block
                try {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " executing task"
                    );

                    task.run();

                } catch (Exception e) {
                    System.out.println(
                            "Task failed: " + e.getMessage()
                    );
                }
            }
        }
    }

    // Wait for all workers to finish
    public void awaitTermination() {

        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Demo
    public static void main(String[] args) {

        CustomThreadPool pool = new CustomThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            final int taskNumber = i;

            pool.submit(() -> {

                System.out.println(
                        "Task " + taskNumber
                                + " started by "
                                + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Task " + taskNumber + " completed"
                );
            });
        }

        pool.shutdown();

        pool.awaitTermination();

        System.out.println("All tasks completed.");
    }
}
