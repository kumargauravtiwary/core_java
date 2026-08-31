package corejava;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockDemo {

    // true = fair locking
    private static final ReentrantLock lock =
            new ReentrantLock(true);

    public static void main(String[] args) {

        Runnable task = () -> {

            String name = Thread.currentThread().getName();

            for (int i = 0; i < 10; i++) {

                lock.lock();

                try {
                    System.out.println(
                            name + " acquired lock"
                    );

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }

                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
