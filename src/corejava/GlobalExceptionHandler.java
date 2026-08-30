package corejava;

/*
                HTTP / Message / RPC
                        │
                        ▼
               ┌─────────────────┐
               │ Request Handler  │
               └────────┬────────┘
                        │
                        ▼
               ┌─────────────────┐
               │ Service / Domain │
               └────────┬────────┘
                        │
                        ▼
               ┌─────────────────┐
               │ Repository / DB  │
               └────────┬────────┘
                        │
                        X
                  Exception
                        │
                        ▼
          ┌──────────────────────────┐
          │ Global Exception Handler │
          └────────────┬─────────────┘
                       │
             ┌─────────┼──────────┐
             ▼         ▼          ▼
         Validation  NotFound   Unexpected
            400        404         500
             │         │           │
             └─────────┴───────────┘
                       │
                       ▼
                Standard Response
*/
import java.util.*;
class ValidationException extends RuntimeException {

    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super("Validation failed");
        this.errors = List.copyOf(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized");
    }
}

public class GlobalExceptionHandler {

    private final Map<Class<? extends Throwable>,
                     ExceptionHandler<? extends Throwable>> handlers;

    public GlobalExceptionHandler() {

        handlers = new HashMap<>();

        register(ValidationException.class,
                this::handleValidation);

        register(NotFoundException.class,
                this::handleNotFound);

        register(UnauthorizedException.class,
                this::handleUnauthorized);

        register(Exception.class,
                this::handleUnexpected);
    }

    public Response handle(Throwable exception) {

        ExceptionHandler<Throwable> handler =
                findHandler(exception);

        try {
            return handler.handle(exception);
        } catch (Exception handlerFailure) {

            // Handler itself failed.
            return new Response(
                    500,
                    "INTERNAL_ERROR",
                    "Internal server error",
                    Map.of()
            );
        }
    }

    @SuppressWarnings("unchecked")
    private ExceptionHandler<Throwable> findHandler(
            Throwable exception) {

        Class<?> exceptionClass =
                exception.getClass();

        // Exact match
        ExceptionHandler<?> handler =
                handlers.get(exceptionClass);

        if (handler != null) {
            return (ExceptionHandler<Throwable>) handler;
        }

        // Find closest superclass
        Class<?> current = exceptionClass;

        while (current != null) {

            handler = handlers.get(current);

            if (handler != null) {
                return (ExceptionHandler<Throwable>) handler;
            }

            current = current.getSuperclass();
        }

        return this::handleUnexpected;
    }

    private <T extends Throwable> void register(
            Class<T> exceptionType,
            ExceptionHandler<T> handler) {

        handlers.put(exceptionType, handler);
    }

    private Response handleValidation(
            ValidationException e) {

        return new Response(
                400,
                "VALIDATION_ERROR",
                "Request validation failed",
                Map.of("errors", e.getErrors())
        );
    }

    private Response handleNotFound(
            NotFoundException e) {

        return new Response(
                404,
                "NOT_FOUND",
                e.getMessage(),
                Map.of()
        );
    }

    private Response handleUnauthorized(
            UnauthorizedException e) {

        return new Response(
                401,
                "UNAUTHORIZED",
                "Authentication required",
                Map.of()
        );
    }

    private Response handleUnexpected(
            Throwable e) {

        // Log complete exception internally.
        System.err.println(
                "Unexpected exception: "
                        + e.getClass().getName()
        );

        e.printStackTrace();

        // Don't expose internal details to client.
        return new Response(
                500,
                "INTERNAL_ERROR",
                "Something went wrong",
                Map.of()
        );
    }

    @FunctionalInterface
    interface ExceptionHandler<T extends Throwable> {
        Response handle(T exception);
    }

    record Response(
            int status,
            String code,
            String message,
            Map<String, Object> details
    ) {}
}
