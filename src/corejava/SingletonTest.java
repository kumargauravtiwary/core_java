package corejava;
/*
getInstance()
      |
      v
instance == null?
   /        \
 YES         NO
  |           |
  v           v
synchronized return
  |
  v
instance == null?
   /       \
 YES        NO
  |          |
  v          |
create       |
instance     |
  |          |
  +----+-----+
       |
       v
    return
*/
class Singleton {

    // volatile is mandatory
    private static volatile Singleton instance;

    // Private constructor prevents external instantiation
    private Singleton() {
    }

    public static Singleton getInstance() {

        // First check - avoids synchronization after initialization
        if (instance == null) {

            synchronized (Singleton.class) {

                // Second check - prevents multiple instances
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
public class SingletonTest {

    public static void main(String[] args)
            throws InterruptedException {

        Runnable task = () -> {

            Singleton instance = Singleton.getInstance();

            System.out.println(
                Thread.currentThread().getName()
                + " -> "
                + instance.hashCode()
            );
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}