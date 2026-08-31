package corejava;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    public void performTask(String threadName) {

        boolean acquired = false;

        try {
            // Wait for the lock for maximum 2 seconds
            acquired = lock.tryLock(2, TimeUnit.SECONDS);

            if (acquired) {

                System.out.println(
                        threadName + " acquired the lock"
                );

                // Simulate some work
                Thread.sleep(5000);

                System.out.println(
                        threadName + " completed the task"
                );

            } else {

                System.out.println(
                        threadName +
                        " could not acquire the lock within 2 seconds"
                );
            }

        } catch (InterruptedException e) {

            System.out.println(
                    threadName + " was interrupted"
            );

            Thread.currentThread().interrupt();

        } finally {

            // Unlock ONLY if this thread acquired the lock
            if (acquired) {
                lock.unlock();
                System.out.println(
                        threadName + " released the lock"
                );
            }
        }
    }

    public static void main(String[] args) {

        TryLockDemo demo = new TryLockDemo();

        Thread t1 = new Thread(
                () -> demo.performTask("Thread-1")
        );

        Thread t2 = new Thread(
                () -> demo.performTask("Thread-2")
        );

        t1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        t2.start();
    }
}
