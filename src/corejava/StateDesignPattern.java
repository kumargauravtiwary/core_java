package corejava;

interface OrderState {

    void pay(Order order);

    void ship(Order order);

    void deliver(Order order);

    void cancel(Order order);

    String getStatus();
}
class Order {

    private final String orderId;
    private OrderState state;

    public Order(String orderId) {
        this.orderId = orderId;
        this.state = new CreatedState();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return state.getStatus();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }
}
class CreatedState implements OrderState {

    @Override
    public void pay(Order order) {

        System.out.println(
                "Payment successful for order "
                        + order.getOrderId()
        );

        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {

        throw new IllegalStateException(
                "Cannot ship an unpaid order"
        );
    }

    @Override
    public void deliver(Order order) {

        throw new IllegalStateException(
                "Cannot deliver an unpaid order"
        );
    }

    @Override
    public void cancel(Order order) {

        System.out.println(
                "Order cancelled: "
                        + order.getOrderId()
        );

        order.setState(new CancelledState());
    }

    @Override
    public String getStatus() {
        return "CREATED";
    }
}
class PaidState implements OrderState {

    @Override
    public void pay(Order order) {

        throw new IllegalStateException(
                "Order has already been paid"
        );
    }

    @Override
    public void ship(Order order) {

        System.out.println(
                "Order shipped: "
                        + order.getOrderId()
        );

        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {

        throw new IllegalStateException(
                "Cannot deliver an order before shipping"
        );
    }

    @Override
    public void cancel(Order order) {

        System.out.println(
                "Payment refunded and order cancelled"
        );

        order.setState(new CancelledState());
    }

    @Override
    public String getStatus() {
        return "PAID";
    }
}
class ShippedState implements OrderState {

    @Override
    public void pay(Order order) {

        throw new IllegalStateException(
                "Order has already been paid"
        );
    }

    @Override
    public void ship(Order order) {

        throw new IllegalStateException(
                "Order has already been shipped"
        );
    }

    @Override
    public void deliver(Order order) {

        System.out.println(
                "Order delivered: "
                        + order.getOrderId()
        );

        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {

        throw new IllegalStateException(
                "Cannot cancel an order after shipping"
        );
    }

    @Override
    public String getStatus() {
        return "SHIPPED";
    }
}
class DeliveredState implements OrderState {

    @Override
    public void pay(Order order) {

        throw new IllegalStateException(
                "Order has already been completed"
        );
    }

    @Override
    public void ship(Order order) {

        throw new IllegalStateException(
                "Order has already been delivered"
        );
    }

    @Override
    public void deliver(Order order) {

        throw new IllegalStateException(
                "Order has already been delivered"
        );
    }

    @Override
    public void cancel(Order order) {

        throw new IllegalStateException(
                "Delivered order cannot be cancelled"
        );
    }

    @Override
    public String getStatus() {
        return "DELIVERED";
    }
}
class CancelledState implements OrderState {

    @Override
    public void pay(Order order) {
        throw new IllegalStateException(
                "Cancelled order cannot be paid"
        );
    }

    @Override
    public void ship(Order order) {
        throw new IllegalStateException(
                "Cancelled order cannot be shipped"
        );
    }

    @Override
    public void deliver(Order order) {
        throw new IllegalStateException(
                "Cancelled order cannot be delivered"
        );
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException(
                "Order is already cancelled"
        );
    }

    @Override
    public String getStatus() {
        return "CANCELLED";
    }
}
public class StateDesignPattern {

    public static void main(String[] args) {

        Order order = new Order("ORD-1001");

        System.out.println(
                "Current state: "
                        + order.getStatus()
        );

        order.pay();

        System.out.println(
                "Current state: "
                        + order.getStatus()
        );

        order.ship();

        System.out.println(
                "Current state: "
                        + order.getStatus()
        );

        order.deliver();

        System.out.println(
                "Current state: "
                        + order.getStatus()
        );
    }
}
