package account;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String name;
    private List<Transaction> transactions = new ArrayList<>();
    private double balance = 0;

    public Account(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
    }

    public void deposit(String reference, double amount) {
        transactions.add(new Transaction(reference, amount));
        balance += amount;
    }

    public void withdraw(String reference, double amount) {
        transactions.add(new Transaction(reference, -amount));
        balance -= amount;
    }

    public String generateStatement() {
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append("Name: ").append(name).append("\n");
        statementBuilder.append("Account: ").append(accountNumber).append("\n");
        statementBuilder.append("Balance: ").append(String.format("%.2f", balance)).append("\n");
        for (Transaction transaction : transactions) {
            statementBuilder.append(transaction.reference).append("\n");
        }
        return statementBuilder.toString();
    }
}

