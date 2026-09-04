package stream;

/*
Is it a UpiPayment?
       │
       ├── NO → try next case
       │
       └── YES
            │
            ▼
       amount > 10000?
            │
       ┌────┴────┐
      YES        NO
       │          │
       ▼          ▼
UPI_LIMIT_CHECK PROCESS_UPI
*/
public class SwitchPatternDemo {

    // Base interface
    sealed interface Payment
            permits CreditCardPayment,
                    UpiPayment,
                    BankTransferPayment {
    }

    // Payment implementations
    record CreditCardPayment(
            double amount,
            String cardNumber
    ) implements Payment {
    }

    record UpiPayment(
            double amount,
            String upiId
    ) implements Payment {
    }

    record BankTransferPayment(
            double amount,
            String accountNumber
    ) implements Payment {
    }

    // Pattern matching + switch expression
    static String processPayment(Payment payment) {

        return switch (payment) {

            case CreditCardPayment p
                    when p.amount() > 100000 ->
                    "HIGH_VALUE_CREDIT_CARD_REVIEW";

            case CreditCardPayment p ->
                    "PROCESS_CREDIT_CARD";

            case UpiPayment p
                    when p.amount() > 10000 ->
                    "UPI_LIMIT_CHECK";

            case UpiPayment p ->
                    "PROCESS_UPI";

            case BankTransferPayment p
                    when p.amount() > 500000 ->
                    "MANUAL_REVIEW";

            case BankTransferPayment p ->
                    "PROCESS_BANK_TRANSFER";
        };
    }

    public static void main(String[] args) {

        Payment[] payments = {

                new CreditCardPayment(
                        5000,
                        "4111111111111111"
                ),

                new CreditCardPayment(
                        250000,
                        "4222222222222222"
                ),

                new UpiPayment(
                        5000,
                        "user@upi"
                ),

                new UpiPayment(
                        25000,
                        "business@upi"
                ),

                new BankTransferPayment(
                        100000,
                        "ACC001"
                ),

                new BankTransferPayment(
                        1000000,
                        "ACC002"
                )
        };

        for (Payment payment : payments) {

            String result =
                    processPayment(payment);

            System.out.println(
                    payment.getClass().getSimpleName()
                            + " -> "
                            + result
            );
        }
    }
}
