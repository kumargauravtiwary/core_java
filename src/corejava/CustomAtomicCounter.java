package corejava;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomAtomicCounter {

    private final AtomicInteger value =
            new AtomicInteger(0);

    public int increment() {

        while (true) {

            int current = value.get();

            int next = current + 1;

            if (value.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public int get() {
        return value.get();
    }

    public static void main(String[] args) {

        CustomAtomicCounter counter =
                new CustomAtomicCounter();

        System.out.println(counter.increment());
        System.out.println(counter.increment());
        System.out.println(counter.increment());

        System.out.println(
                "Final value = " + counter.get()
        );
    }
}
