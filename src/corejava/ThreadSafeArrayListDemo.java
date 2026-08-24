package corejava;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class ThreadSafeArrayList<E> implements Iterable<E> {

    private final CopyOnWriteArrayList<E> list =
            new CopyOnWriteArrayList<>();

    public boolean add(E element) {
        return list.add(element);
    }

    public E get(int index) {
        return list.get(index);
    }

    public E set(int index, E element) {
        return list.set(index, element);
    }

    public boolean remove(E element) {
        return list.remove(element);
    }

    public E remove(int index) {
        return list.remove(index);
    }

    public boolean contains(E element) {
        return list.contains(element);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

public class ThreadSafeArrayListDemo {

    public static void main(String[] args) {

        ThreadSafeArrayList<String> users =
                new ThreadSafeArrayList<>();

        users.add("Rahul");
        users.add("Amit");
        users.add("Priya");

        System.out.println(users);

        System.out.println("First user: " + users.get(0));

        System.out.println("Contains Amit: "
                + users.contains("Amit"));

        System.out.println("Size: " + users.size());
    }
}