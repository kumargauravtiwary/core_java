package corejava;

sealed interface Payment
        permits CreditCardPayment,
                UpiPayment,
                CashPayment {
}

final class CreditCardPayment implements Payment {

    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String cardNumber() {
        return cardNumber;
    }
}

final class UpiPayment implements Payment {

    private final String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    public String upiId() {
        return upiId;
    }
}

final class CashPayment implements Payment {

    private final double amount;

    public CashPayment(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}

public class SealedPatternDemo {

    public static String process(Payment payment) {

        return switch (payment) {

            case CreditCardPayment cc ->
                    "Processing credit card: " + cc.cardNumber();

            case UpiPayment upi ->
                    "Processing UPI: " + upi.upiId();

            case CashPayment cash ->
                    "Processing cash: ₹" + cash.amount();
        };
    }

    public static void main(String[] args) {

        Payment p1 =
                new CreditCardPayment("1234-5678");

        Payment p2 =
                new UpiPayment("gaurav@upi");

        Payment p3 =
                new CashPayment(5000);

        System.out.println(process(p1));
        System.out.println(process(p2));
        System.out.println(process(p3));
    }
}
