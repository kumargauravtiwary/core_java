package corejava;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureErrorHandling {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    if (true) {
                        throw new RuntimeException("Database unavailable");
                    }
                    return "Customer Data";
                })
                .thenApply(data -> data.toUpperCase())
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return "DEFAULT CUSTOMER";
                });

        System.out.println(future.join());
    }
}
