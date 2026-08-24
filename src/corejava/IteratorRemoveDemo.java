package corejava;

import java.util.*;

public class IteratorRemoveDemo {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(
                Arrays.asList(
                        "Amit",
                        "Rahul",
                        "Priya",
                        "Neha"
                )
        );

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {

            String name = iterator.next();

            if (name.equals("Rahul")) {
                iterator.remove();
            }
        }

        System.out.println(names);
    }
}
