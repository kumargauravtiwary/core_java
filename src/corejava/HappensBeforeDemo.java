package corejava;

public class HappensBeforeDemo {

    private int data = 0;

    // volatile establishes a happens-before relationship
    private volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {

        HappensBeforeDemo demo = new HappensBeforeDemo();

        Thread writer = new Thread(() -> {

            // 1. Normal write
            demo.data = 42;

            // 2. Volatile write
            demo.ready = true;

            System.out.println("Writer: data = 42");
            System.out.println("Writer: ready = true");
        });


        Thread reader = new Thread(() -> {

            // Wait until writer changes ready
            while (!demo.ready) {
                // Busy wait
            }

            // Because ready is volatile:
            // write to data happens-before this read
            System.out.println("Reader: ready = true");
            System.out.println("Reader: data = " + demo.data);
        });


        reader.start();
        writer.start();

        writer.join();
        reader.join();

        System.out.println("Main thread finished");
    }
}
