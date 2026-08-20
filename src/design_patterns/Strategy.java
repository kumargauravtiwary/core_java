package design_patterns;

//Allows changing an algorithm at runtime.

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardStrategy implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Credit Card: " + amount);
    }
}

class UPIStrategy implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("UPI: " + amount);
    }
}

class PaymentContext {

    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}

public class Strategy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentContext context =
		        new PaymentContext(new UPIStrategy());

		context.pay(1000);

		context =
		        new PaymentContext(new CreditCardStrategy());

		context.pay(5000);

	}

}
