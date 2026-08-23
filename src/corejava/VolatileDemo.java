package corejava;

class Worker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {

        System.out.println("Worker started");

        while (running) {
            // Do some work
        }

        System.out.println("Worker stopped");
    }

    public void stop() {
        running = false;
    }
}

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        Thread thread = new Thread(worker);
        thread.start();

        Thread.sleep(1000);

        worker.stop();

        System.out.println("Stop requested");
    }
}
