package corejava;
/*
                 Stage 1
              ┌───────────┐
              │ Producers │
              │ P1   P2   │
              └─────┬─────┘
                    │
                    ▼
             ┌──────────────┐
             │   Queue #1   │
             └──────┬───────┘
                    │
                    ▼
              Stage 2
        ┌────────────────────┐
        │   Transformers     │
        │ T1   T2   T3       │
        └─────────┬──────────┘
                  │
                  ▼
           ┌─────────────┐
           │  Queue #2   │
           └──────┬──────┘
                  │
                  ▼
              Stage 3
        ┌─────────────────┐
        │    Validators   │
        │      V1   V2    │
        └────────┬────────┘
                 │
                 ▼
          ┌─────────────┐
          │   Queue #3  │
          └──────┬──────┘
                 │
                 ▼
              Stage 4
        ┌─────────────────┐
        │    Consumers   │
        │      C1   C2   │
        └─────────────────┘
*/

import java.util.concurrent.*;

public class MultiStagePipeline {

    record Data(int id, String value) {}

    static final Data POISON = new Data(-1, "POISON");

    public static void main(String[] args) throws Exception {

        int producers = 2;
        int transformers = 3;
        int validators = 2;
        int consumers = 2;

        BlockingQueue<Data> queue1 =
                new ArrayBlockingQueue<>(20);

        BlockingQueue<Data> queue2 =
                new ArrayBlockingQueue<>(20);

        BlockingQueue<Data> queue3 =
                new ArrayBlockingQueue<>(20);

        ExecutorService producerPool =
                Executors.newFixedThreadPool(producers);

        ExecutorService transformerPool =
                Executors.newFixedThreadPool(transformers);

        ExecutorService validatorPool =
                Executors.newFixedThreadPool(validators);

        ExecutorService consumerPool =
                Executors.newFixedThreadPool(consumers);

        // ==========================================
        // PRODUCER
        // ==========================================

        for (int p = 0; p < producers; p++) {

            int producerId = p;

            producerPool.submit(() -> {

                try {
                    for (int i = 1; i <= 10; i++) {

                        Data data =
                                new Data(
                                        producerId * 100 + i,
                                        "item-" + i
                                );

                        queue1.put(data);

                        System.out.println(
                                "PRODUCER-" + producerId
                                        + " -> " + data
                        );
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        producerPool.shutdown();
        producerPool.awaitTermination(1, TimeUnit.MINUTES);

        // Tell transformers that production is finished
        for (int i = 0; i < transformers; i++) {
            queue1.put(POISON);
        }

        // ==========================================
        // TRANSFORMER
        // ==========================================

        for (int t = 0; t < transformers; t++) {

            int transformerId = t;

            transformerPool.submit(() -> {

                try {
                    while (true) {

                        Data data = queue1.take();

                        if (data == POISON) {
                            break;
                        }

                        Data transformed =
                                new Data(
                                        data.id(),
                                        data.value().toUpperCase()
                                );

                        System.out.println(
                                "TRANSFORMER-" + transformerId
                                        + " -> " + transformed
                        );

                        queue2.put(transformed);
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        transformerPool.shutdown();
        transformerPool.awaitTermination(1, TimeUnit.MINUTES);

        // Tell validators transformation is complete
        for (int i = 0; i < validators; i++) {
            queue2.put(POISON);
        }

        // ==========================================
        // VALIDATOR
        // ==========================================

        for (int v = 0; v < validators; v++) {

            int validatorId = v;

            validatorPool.submit(() -> {

                try {
                    while (true) {

                        Data data = queue2.take();

                        if (data == POISON) {
                            break;
                        }

                        // Validation logic
                        if (data.value() != null &&
                                !data.value().isBlank()) {

                            System.out.println(
                                    "VALIDATOR-" + validatorId
                                            + " -> " + data
                            );

                            queue3.put(data);
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        validatorPool.shutdown();
        validatorPool.awaitTermination(1, TimeUnit.MINUTES);

        // Tell consumers validation is complete
        for (int i = 0; i < consumers; i++) {
            queue3.put(POISON);
        }

        // ==========================================
        // CONSUMER
        // ==========================================

        for (int c = 0; c < consumers; c++) {

            int consumerId = c;

            consumerPool.submit(() -> {

                try {
                    while (true) {

                        Data data = queue3.take();

                        if (data == POISON) {
                            break;
                        }

                        System.out.println(
                                "CONSUMER-" + consumerId
                                        + " PROCESSED -> " + data
                        );
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        consumerPool.shutdown();
        consumerPool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\n=== PIPELINE COMPLETED ===");
    }
}
