package stream;

import java.util.function.Supplier;

class ExpensiveObject {

    public ExpensiveObject() {
        System.out.println("Creating expensive object...");

        // Simulate expensive initialization
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Expensive object created!");
    }

    public void doWork() {
        System.out.println("Expensive object is doing work...");
    }
}

class Lazy<T> {

    private final Supplier<T> supplier;
    private T value;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {

        if (value == null) {
            System.out.println("Initializing lazily...");
            value = supplier.get();
        }

        return value;
    }
}

public class SupplierLazyInitializationDemo {

    public static void main(String[] args) {

        System.out.println("Application started.");

        // Object is NOT created here
        Lazy<ExpensiveObject> lazyObject =
                new Lazy<>(ExpensiveObject::new);

        System.out.println("Lazy object created.");

        System.out.println("\nNothing expensive has happened yet.");

        // First get() -> object is created
        System.out.println("\nFirst get():");

        ExpensiveObject object1 = lazyObject.get();

        object1.doWork();

        // Second get() -> existing object is returned
        System.out.println("\nSecond get():");

        ExpensiveObject object2 = lazyObject.get();

        object2.doWork();

        // Verify same instance
        System.out.println(
                "\nSame object? " + (object1 == object2)
        );
    }
}
