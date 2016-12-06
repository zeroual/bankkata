package com.zeros.bankkata;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Account {

    private List<Transaction> transactions = new ArrayList<>();

    public void deposit(double amount) {
        this.transactions.add(new Transaction(amount));
    }

    public void withdraw(double amount) {
        this.transactions.add(new Transaction(-amount));
    }

    public double runningBalance() {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .sum();
    }

    public List<Transaction> transactions() {
        return unmodifiableList(this.transactions);
    }
}
