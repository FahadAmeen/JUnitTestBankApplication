package com.FahadAmeen;

public class BankAccount {

    private int id;
    private double balance = 0;
    private static int lastId = 0;

    public BankAccount() {
        this.id = ++lastId;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Package-private, for internal use only, for example, for testing.
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        handleNegativeAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        handleNegativeAmount(amount);
        if (balance - amount < 0) {
            throw new IllegalArgumentException
                    ("Cannot withdraw " + amount + " from " + balance);
        }
        balance -= amount;
    }

    private void handleNegativeAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount: " + amount);
        }
    }
}
