package corejava;

import java.math.BigDecimal;

/*
                BankAccount
                     │
        ┌────────────┴────────────┐
        │                         │
   private data              public methods
        │                         │
        │                  ┌──────┴──────┐
        │                  │             │
     balance           setter()      withdraw()
        │                  │             │
        │              validation     validation
        │                  │             │
        └──────────────────┴─────────────┘
*/
class BankAccount {

    // Encapsulated fields
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal balance;
    private String accountType;

    public BankAccount(
            String accountNumber,
            String accountHolderName,
            BigDecimal balance,
            String accountType) {

        setAccountNumber(accountNumber);
        setAccountHolderName(accountHolderName);
        setBalance(balance);
        setAccountType(accountType);
    }

    // Getter
    public String getAccountNumber() {
        return accountNumber;
    }

    // Setter with validation
    public void setAccountNumber(String accountNumber) {

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Account number cannot be empty"
            );
        }

        if (!accountNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException(
                    "Account number must contain exactly 10 digits"
            );
        }

        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {

        if (accountHolderName == null ||
                accountHolderName.isBlank()) {

            throw new IllegalArgumentException(
                    "Account holder name cannot be empty"
            );
        }

        if (accountHolderName.length() < 3) {
            throw new IllegalArgumentException(
                    "Name must contain at least 3 characters"
            );
        }

        this.accountHolderName = accountHolderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {

        if (balance == null) {
            throw new IllegalArgumentException(
                    "Balance cannot be null"
            );
        }

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Balance cannot be negative"
            );
        }

        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {

        if (accountType == null) {
            throw new IllegalArgumentException(
                    "Account type cannot be null"
            );
        }

        if (!accountType.equals("SAVINGS") &&
                !accountType.equals("CURRENT")) {

            throw new IllegalArgumentException(
                    "Account type must be SAVINGS or CURRENT"
            );
        }

        this.accountType = accountType;
    }

    // Business operation
    public void deposit(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Deposit amount must be positive"
            );
        }

        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Withdrawal amount must be positive"
            );
        }

        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException(
                    "Insufficient balance"
            );
        }

        balance = balance.subtract(amount);
    }
}



public class Encapsulation {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(
                "1234567890",
                "Kumar Gaurav",
                new BigDecimal("50000"),
                "SAVINGS"
        );

        System.out.println(account.getBalance());

        account.deposit(new BigDecimal("10000"));

        System.out.println(account.getBalance());

        account.withdraw(new BigDecimal("5000"));

        System.out.println(account.getBalance());

        // Invalid operation
        account.setBalance(new BigDecimal("-1000"));
    }
}
