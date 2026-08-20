package design_patterns;

interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

class UPIPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("Paid using UPI");
    }
}
//Creates objects without exposing the object-creation logic to the client.
class PaymentFactory {

    public static Payment createPayment(String type) {

        return switch (type.toUpperCase()) {
            case "CREDIT_CARD" -> new CreditCardPayment();
            case "UPI" -> new UPIPayment();
            default -> throw new IllegalArgumentException("Invalid payment type");
        };
    }
}

class Factory{
		public static void main(String[] args) {
		Payment payment1 = PaymentFactory.createPayment("CREDIT_CARD");
		payment1.pay(); // Output: Paid using Credit Card

		Payment payment2 = PaymentFactory.createPayment("UPI");
		payment2.pay(); // Output: Paid using UPI
	}
}