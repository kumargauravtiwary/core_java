package design_patterns;

//Allows incompatible interfaces to work together.

interface PaymentProcessor {
    void pay(double amount);
}

class LegacyPaymentSystem {

    public void makePayment(double amount) {
        System.out.println("Legacy payment: " + amount);
    }
}

class PaymentAdapter implements PaymentProcessor {

    private final LegacyPaymentSystem legacySystem;

    public PaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void pay(double amount) {
        legacySystem.makePayment(amount);
    }
}

public class Adapter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LegacyPaymentSystem legacy = new LegacyPaymentSystem();

		PaymentProcessor processor =
		        new PaymentAdapter(legacy);

		processor.pay(5000);
	}

}
