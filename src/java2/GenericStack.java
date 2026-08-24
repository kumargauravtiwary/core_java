package java2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class GenericStack<T> {

    private final List<T> elements = new ArrayList<>();

    // Push an element onto the stack
    public void push(T element) {
        elements.add(element);
    }

    // Remove and return the top element
    public T pop() {

        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.remove(elements.size() - 1);
    }

    // Return the top element without removing it
    public T peek() {

        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public static void main(String[] args) {

        // Integer Stack
        GenericStack<Integer> numbers = new GenericStack<>();

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println("Stack: " + numbers);

        System.out.println("Peek: " + numbers.peek());

        System.out.println("Pop: " + numbers.pop());

        System.out.println("Stack after pop: " + numbers);

        // String Stack
        GenericStack<String> names = new GenericStack<>();

        names.push("Java");
        names.push("Spring");
        names.push("Kafka");

        System.out.println("\nString Stack: " + names);
        System.out.println("Peek: " + names.peek());
        System.out.println("Pop: " + names.pop());
    }
}
