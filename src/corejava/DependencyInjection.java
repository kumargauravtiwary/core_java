package corejava;

interface NotificationService {

    void send(String message);
}
class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}
class SmsNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
class OrderService {

    private final NotificationService notificationService;

    // Constructor Injection
    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder() {

        System.out.println("Order placed");

        notificationService.send("Order confirmation");
    }
}
public class DependencyInjection {

    public static void main(String[] args) {

        NotificationService emailService =
                new EmailNotificationService();

        NotificationService smsNotification =
                new SmsNotificationService();

        OrderService orderService =
                new OrderService(smsNotification);

        orderService.placeOrder();
    }
}
