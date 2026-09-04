package stream;

/*
                    TransactionCollection
                            │
                            ▼
                  Custom Spliterator
                            │
             ┌──────────────┴──────────────┐
             │                             │
        tryAdvance()                  trySplit()
             │                             │
       Process one item             Divide collection
             │                             │
             ▼                    ┌────────┴────────┐
          Stream                  │                 │
                                  ▼                 ▼
                               Part 1            Part 2
                                  │                 │
                                  ▼                 ▼
                              Thread 1           Thread 2
*/
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class CustomSpliteratorDemo {

    // Domain object
    record Transaction(
            String id,
            String customer,
            BigDecimal amount,
            String type
    ) {}

    // Domain-specific collection
    static class TransactionCollection implements Iterable<Transaction> {

        private final List<Transaction> transactions;

        public TransactionCollection(List<Transaction> transactions) {
            this.transactions = List.copyOf(transactions);
        }

        @Override
        public Iterator<Transaction> iterator() {
            return transactions.iterator();
        }

        @Override
        public Spliterator<Transaction> spliterator() {
            return new TransactionSpliterator(
                    transactions,
                    0,
                    transactions.size()
            );
        }

        public int size() {
            return transactions.size();
        }
    }

    // Custom Spliterator
    static class TransactionSpliterator
            implements Spliterator<Transaction> {

        private final List<Transaction> transactions;

        private int current;
        private final int end;

        public TransactionSpliterator(
                List<Transaction> transactions,
                int start,
                int end) {

            this.transactions = transactions;
            this.current = start;
            this.end = end;
        }

        // Processes the next transaction
        @Override
        public boolean tryAdvance(
                Consumer<? super Transaction> action) {

            if (current < end) {

                Transaction transaction =
                        transactions.get(current++);

                action.accept(transaction);

                return true;
            }

            return false;
        }

        // Splits the collection for parallel processing
        @Override
        public Spliterator<Transaction> trySplit() {

            int remaining = end - current;

            if (remaining <= 1) {
                return null;
            }

            int mid = current + remaining / 2;

            Spliterator<Transaction> prefix =
                    new TransactionSpliterator(
                            transactions,
                            current,
                            mid
                    );

            current = mid;

            return prefix;
        }

        // Estimated number of remaining elements
        @Override
        public long estimateSize() {
            return end - current;
        }

        // Characteristics of our Spliterator
        @Override
        public int characteristics() {

            return ORDERED
                    | SIZED
                    | SUBSIZED
                    | IMMUTABLE;
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = List.of(

                new Transaction(
                        "T001",
                        "Alice",
                        new BigDecimal("5000"),
                        "PAYMENT"
                ),

                new Transaction(
                        "T002",
                        "Bob",
                        new BigDecimal("12000"),
                        "PAYMENT"
                ),

                new Transaction(
                        "T003",
                        "Charlie",
                        new BigDecimal("7500"),
                        "REFUND"
                ),

                new Transaction(
                        "T004",
                        "David",
                        new BigDecimal("20000"),
                        "PAYMENT"
                ),

                new Transaction(
                        "T005",
                        "Eva",
                        new BigDecimal("15000"),
                        "PAYMENT"
                ),

                new Transaction(
                        "T006",
                        "Frank",
                        new BigDecimal("3000"),
                        "REFUND"
                )
        );

        TransactionCollection collection =
                new TransactionCollection(transactions);

        // Sequential stream
        System.out.println("Sequential Processing:");

        StreamSupport.stream(
                        collection.spliterator(),
                        false
                )
                .filter(t ->
                        t.type().equals("PAYMENT"))
                .forEach(System.out::println);

        // Parallel stream
        System.out.println("\nParallel Processing:");

        StreamSupport.stream(
                        collection.spliterator(),
                        true
                )
                .filter(t ->
                        t.type().equals("PAYMENT"))
                .forEach(t ->
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " -> "
                                        + t
                        )
                );

        // Calculate total payment amount
        BigDecimal total =
                StreamSupport.stream(
                                collection.spliterator(),
                                false
                        )
                        .filter(t ->
                                t.type().equals("PAYMENT"))
                        .map(Transaction::amount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        System.out.println(
                "\nTotal Payments = " + total
        );
    }
}
