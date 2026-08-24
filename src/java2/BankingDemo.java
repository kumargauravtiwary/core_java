package java2;
/*
BankingException
│
├── AccountException
│   ├── AccountNotFoundException
│   ├── AccountAlreadyExistsException
│   └── AccountClosedException
│
├── TransactionException
│   ├── InsufficientFundsException
│   ├── InvalidAmountException
│   ├── DailyLimitExceededException
│   └── TransactionNotAllowedException
│
└── AuthenticationException
    ├── InvalidPinException
    └── AccountLockedException
*/
class BankingException extends Exception {

    public BankingException(String message) {
        super(message);
    }

    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}
class AccountException extends BankingException {

    public AccountException(String message) {
        super(message);
    }
}
class AccountNotFoundException extends AccountException {

    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber);
    }
}
class AccountAlreadyExistsException extends AccountException {

    public AccountAlreadyExistsException(String accountNumber) {
        super("Account already exists: " + accountNumber);
    }
}
class AccountClosedException extends AccountException {

    public AccountClosedException(String accountNumber) {
        super("Account is closed: " + accountNumber);
    }
}
class TransactionException extends BankingException {

    public TransactionException(String message) {
        super(message);
    }
}
class InsufficientFundsException extends TransactionException {

    private final double balance;
    private final double requestedAmount;

    public InsufficientFundsException(
            double balance,
            double requestedAmount) {

        super(String.format(
                "Insufficient funds. Balance: %.2f, Requested: %.2f",
                balance,
                requestedAmount
        ));

        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public double getBalance() {
        return balance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
class InvalidAmountException extends TransactionException {

    public InvalidAmountException(double amount) {
        super("Invalid transaction amount: " + amount);
    }
}
class DailyLimitExceededException extends TransactionException {

    private final double dailyLimit;
    private final double requestedAmount;

    public DailyLimitExceededException(
            double dailyLimit,
            double requestedAmount) {

        super(String.format(
                "Daily transaction limit exceeded. Limit: %.2f, Requested: %.2f",
                dailyLimit,
                requestedAmount
        ));

        this.dailyLimit = dailyLimit;
        this.requestedAmount = requestedAmount;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
class AuthenticationException extends BankingException {

    public AuthenticationException(String message) {
        super(message);
    }
}
class InvalidPinException extends AuthenticationException {

    public InvalidPinException() {
        super("Invalid PIN.");
    }
}
class AccountLockedException extends AuthenticationException {

    public AccountLockedException(String accountNumber) {
        super("Account is locked: " + accountNumber);
    }
}
class BankAccount {

    private final String accountNumber;
    private double balance;
    private boolean closed;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void withdraw(double amount)
            throws InvalidAmountException,
                   InsufficientFundsException,
                   AccountClosedException {

        if (closed) {
            throw new AccountClosedException(accountNumber);
        }

        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    balance,
                    amount
            );
        }

        balance -= amount;

        System.out.println(
                "Withdrawal successful: " + amount
        );
    }

    public void closeAccount() {
        closed = true;
    }

    public double getBalance() {
        return balance;
    }
}
public class BankingDemo {

    public static void main(String[] args) {

        BankAccount account =
                new BankAccount("ACC-1001", 5000);

        try {

            account.withdraw(7000);

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Business error: " + e.getMessage()
            );

            System.out.println(
                    "Available balance: " + e.getBalance()
            );

        } catch (TransactionException e) {

            System.out.println(
                    "Transaction failed: " + e.getMessage()
            );

        } catch (BankingException e) {

            System.out.println(
                    "Banking error: " + e.getMessage()
            );
        }
    }
}
