package corejava;

/*
| Feature                       | Fail-Fast                                        | Fail-Safe*                                                |
| ----------------------------- | ------------------------------------------------ | --------------------------------------------------------- |
| Example                       | `ArrayList`                                      | `CopyOnWriteArrayList`                                    |
| Modification during iteration | Usually throws `ConcurrentModificationException` | Doesn't throw CME for normal collection modification      |
| Iterator view                 | Original collection                              | Snapshot / weakly consistent view depending on collection |
| New elements visible?         | Iteration fails                                  | `CopyOnWriteArrayList`: No                                |
| Extra memory                  | Usually lower                                    | `CopyOnWriteArrayList`: snapshot/copy overhead            |
| Best for                      | Normal iteration                                 | Concurrent/read-heavy scenarios                           |

*/
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailSafeDemo {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> names =
                new CopyOnWriteArrayList<>();

        names.add("Amit");
        names.add("Rahul");
        names.add("Priya");

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {

            String name = iterator.next();

            System.out.println(name);

            if (name.equals("Rahul")) {

                names.add("Neha");

                System.out.println(
                        "Added Neha to the collection"
                );
            }
        }

        System.out.println("Final list: " + names);
    }
}
