package corejava;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationDemo {

    private static final ReentrantLock lock =
            new ReentrantLock(false); // unfair

    public static void main(String[] args) {

        Runnable task = () -> {

            String name = Thread.currentThread().getName();

            for (int i = 0; i < 20; i++) {

                lock.lock();

                try {
                    System.out.println(
                            name + " acquired lock"
                    );

                    // Very short critical section
                    System.out.println(
                            name + " doing work"
                    );

                } finally {
                    lock.unlock();
                }
            }
        };

        Thread fastThread = new Thread(task, "Fast-Thread");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        fastThread.start();
        thread2.start();
        thread3.start();
    }
}
