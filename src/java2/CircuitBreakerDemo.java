package java2;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

enum CircuitState {
    CLOSED,
    OPEN,
    HALF_OPEN
}

class CircuitBreakerOpenException extends RuntimeException {

    public CircuitBreakerOpenException(String message) {
        super(message);
    }
}

class CircuitBreaker {

    private final int failureThreshold;
    private final Duration openTimeout;

    private CircuitState state = CircuitState.CLOSED;
    private int failureCount = 0;
    private Instant openedAt;

    public CircuitBreaker(
            int failureThreshold,
            Duration openTimeout) {

        if (failureThreshold <= 0) {
            throw new IllegalArgumentException(
                    "Failure threshold must be > 0"
            );
        }

        this.failureThreshold = failureThreshold;
        this.openTimeout = openTimeout;
    }

    public synchronized <T> T execute(
            Callable<T> operation) throws Exception {

        checkState();

        if (state == CircuitState.OPEN) {
            throw new CircuitBreakerOpenException(
                    "Circuit breaker is OPEN"
            );
        }

        try {

            T result = operation.call();

            // Successful call
            onSuccess();

            return result;

        } catch (Exception e) {

            // Failed call
            onFailure();

            throw e;
        }
    }

    private void checkState() {

        if (state == CircuitState.OPEN) {

            Duration elapsed =
                    Duration.between(openedAt, Instant.now());

            if (elapsed.compareTo(openTimeout) >= 0) {

                state = CircuitState.HALF_OPEN;

                System.out.println(
                        "Circuit changed: OPEN -> HALF_OPEN"
                );
            }
        }
    }

    private void onSuccess() {

        failureCount = 0;

        if (state == CircuitState.HALF_OPEN) {

            state = CircuitState.CLOSED;

            System.out.println(
                    "Circuit changed: HALF_OPEN -> CLOSED"
            );
        }
    }

    private void onFailure() {

        failureCount++;

        System.out.println(
                "Failure count = " + failureCount
        );

        if (failureCount >= failureThreshold) {

            state = CircuitState.OPEN;
            openedAt = Instant.now();

            System.out.println(
                    "Circuit changed: CLOSED -> OPEN"
            );
        }
    }

    public synchronized CircuitState getState() {
        return state;
    }

    public synchronized int getFailureCount() {
        return failureCount;
    }
}
class PaymentService {

    private int attempts = 0;

    public String charge() {

        attempts++;

        System.out.println(
                "Calling payment service..."
        );

        // Simulate first 3 calls failing
        if (attempts <= 3) {
            throw new RuntimeException(
                    "Payment service unavailable"
            );
        }

        return "Payment successful";
    }
}
public class CircuitBreakerDemo {

    public static void main(String[] args)
            throws InterruptedException {

        PaymentService paymentService =
                new PaymentService();

        CircuitBreaker circuitBreaker =
                new CircuitBreaker(
                        3,
                        Duration.ofSeconds(5)
                );

        for (int i = 1; i <= 8; i++) {

            System.out.println(
                    "\nAttempt #" + i
            );

            try {

                String result =
                        circuitBreaker.execute(
                                paymentService::charge
                        );

                System.out.println(
                        "Result: " + result
                );

            } catch (CircuitBreakerOpenException e) {

                System.out.println(
                        "Request rejected: "
                                + e.getMessage()
                );

            } catch (Exception e) {

                System.out.println(
                        "Service error: "
                                + e.getMessage()
                );
            }

            System.out.println(
                    "State: "
                            + circuitBreaker.getState()
            );

            /*
             * Wait long enough after the circuit opens
             * to demonstrate HALF_OPEN.
             */
            if (i == 6) {
                Thread.sleep(6000);
            }
        }
    }
}

