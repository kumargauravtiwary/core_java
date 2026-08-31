package corejava;

public class RaceConditionDemo {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> incrementCounter());
        Thread t2 = new Thread(() -> incrementCounter());
        Thread t3 = new Thread(() -> incrementCounter());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Expected: 300000");
        System.out.println("Actual:   " + counter);
    }

    static void incrementCounter() {

        for (int i = 0; i < 100_000; i++) {
            counter++;
        }
    }
}
