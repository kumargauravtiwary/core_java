package corejava;

public class EnumAbstractMethodDemo {

    public static void main(String[] args) {

        PaymentMethod[] methods = PaymentMethod.values();

        for (PaymentMethod method : methods) {

            System.out.println("Method   : " + method);
            System.out.println("Fee      : " + method.getFee());
            System.out.println("Process  : " + method.process(1000));
            System.out.println();
        }
    }
}


enum PaymentMethod {

    CREDIT_CARD("Credit Card", 25) {

        @Override
        public String process(double amount) {
            return "Processing ₹" + amount
                    + " using Credit Card";
        }
    },

    DEBIT_CARD("Debit Card", 10) {

        @Override
        public String process(double amount) {
            return "Processing ₹" + amount
                    + " using Debit Card";
        }
    },

    UPI("UPI", 0) {

        @Override
        public String process(double amount) {
            return "Processing ₹" + amount
                    + " using UPI";
        }
    },

    NET_BANKING("Net Banking", 5) {

        @Override
        public String process(double amount) {
            return "Processing ₹" + amount
                    + " using Net Banking";
        }
    };

    // =====================================================
    // Enum fields
    // =====================================================

    private final String displayName;
    private final double fee;


    // =====================================================
    // Enum constructor
    // =====================================================

    PaymentMethod(String displayName, double fee) {
        this.displayName = displayName;
        this.fee = fee;
    }


    // =====================================================
    // Getter
    // =====================================================

    public String getDisplayName() {
        return displayName;
    }

    public double getFee() {
        return fee;
    }


    // =====================================================
    // Abstract method
    // =====================================================

    public abstract String process(double amount);
}
