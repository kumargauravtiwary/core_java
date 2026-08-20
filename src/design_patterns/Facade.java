package design_patterns;

//Provides a simple interface over a complex subsystem.

class InventoryService {

    public boolean checkStock(String product) {
        System.out.println("Checking inventory");
        return true;
    }
}

class PaymentService {

    public void pay(double amount) {
        System.out.println("Payment successful");
    }
}

class ShippingService {

    public void ship(String product) {
        System.out.println("Product shipped");
    }
}

class OrderFacade {

    private final InventoryService inventory;
    private final PaymentService payment;
    private final ShippingService shipping;

    public OrderFacade() {
        inventory = new InventoryService();
        payment = new PaymentService();
        shipping = new ShippingService();
    }

    public void placeOrder(String product, double amount) {

        if (inventory.checkStock(product)) {
            payment.pay(amount);
            shipping.ship(product);
        }
    }
}

public class Facade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderFacade orderFacade = new OrderFacade();

		orderFacade.placeOrder("Laptop", 75000);
	}

}
