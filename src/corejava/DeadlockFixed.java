package corejava;

public class DeadlockFixed {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread-1 acquired LOCK_A");

                sleep();

                synchronized (LOCK_B) {
                    System.out.println("Thread-1 acquired LOCK_B");
                    System.out.println("Thread-1 completed");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            // Same lock ordering: LOCK_A -> LOCK_B
            synchronized (LOCK_A) {
                System.out.println("Thread-2 acquired LOCK_A");

                sleep();

                synchronized (LOCK_B) {
                    System.out.println("Thread-2 acquired LOCK_B");
                    System.out.println("Thread-2 completed");
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
