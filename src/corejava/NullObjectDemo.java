package corejava;

/*
                 NotificationService
                         ▲
              ┌──────────┴──────────┐
              │                     │
              │                     │
   EmailNotificationService   NullNotificationService
              │                     │
       send email              do nothing
       
*/
interface NotificationService {

    void send(String message);
}
class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
class NullNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        // Do nothing intentionally
    }
}
class OrderService {

    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String orderId) {

        System.out.println("Order " + orderId + " placed.");

        notificationService.send(
                "Order " + orderId + " placed successfully."
        );
    }
}
public class NullObjectDemo {

    public static void main(String[] args) {

        // Customer wants email notifications
        NotificationService emailService =
                new EmailNotificationService();

        OrderService orderWithEmail =
                new OrderService(emailService);

        orderWithEmail.placeOrder("ORD-101");


        System.out.println();


        // Customer does not want notifications
        NotificationService nullService =
                new NullNotificationService();

        OrderService orderWithoutNotification =
                new OrderService(nullService);

        orderWithoutNotification.placeOrder("ORD-102");
    }
}

